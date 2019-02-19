package com.tyella.seckill.service;

import com.alibaba.fastjson.JSON;
import com.tyella.seckill.common.SecKillEnum;
import com.tyella.seckill.dao.ProductDao;
import com.tyella.seckill.dao.UserDao;
import com.tyella.seckill.entity.*;
import com.tyella.seckill.exception.SecKillException;
import com.tyella.seckill.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SecKillService {

    public static final Logger log= LoggerFactory.getLogger(SecKillService.class);

    @Autowired
    JedisUtil jedisUtil;

    @Autowired
    MQUtil mqUtil;

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    SecKillEnum secKillEnum;

    public SecKillEnum handleByRedisWatch(Map<String, Object> paramMap) {
        Record record;

        Integer userId = (Integer) paramMap.get("userId");
        Integer productId = (Integer) paramMap.get("productId");
        User user = userDao.getUserById(userId);
        Product product = productDao.getProductById(productId);

        String productStockCacheKey = product.getProductName() + "_stock";
        String hasBoughtSetKey = SecKillUtil.getRedisHasBoughtSetKey(product.getProductName());

        //watch开启监控
        //redis watch命令用于监视一个或多个key,如果事务执行前key被其他命令改动，那么操作将被打断
        jedisUtil.watch(productStockCacheKey);

        //判断是否重复购买，注意这里高并发情形下并不安全
        //SISMEMBER:判断成员元素是否是集合的成员
        //是返回1，不是返回0
        boolean isBuy = jedisUtil.sismember(hasBoughtSetKey, String.valueOf(user.getId()));
        if (isBuy) {
            log.error("用户:" + user.getUserName() + "重复购买商品" + product.getProductName());
            throw new SecKillException(SecKillEnum.REPEAT);
        }


        String stock = jedisUtil.get(productStockCacheKey);
        if (Integer.parseInt(stock) <= 0) {
            log.error("商品:" + product.getProductName() + "库存不足!");
            throw new SecKillException(SecKillEnum.LOW_STOCKS);
        }

        //redis MULTI:标志着一个事务的开启，书屋块内的多条命令会按照顺序被放到一个队列中，最终有exec命令原子性的执行
        //开启Redis事务
        Transaction tx = jedisUtil.multi();
        //库存减一
        //DECRBY命令将key所存储的值减去指定的数量
        tx.decrBy(productStockCacheKey, 1);
        //执行事务
        List<Object> resultList = tx.exec();

        if (resultList == null || resultList.isEmpty()) {
            jedisUtil.unwatch();
            //watch监控被更改过----物品抢购失败;
            log.error("商品:" + product.getProductName() + ",watch监控被更改,物品抢购失败");
            throw new SecKillException(SecKillEnum.FAIL);
        }

        //添加到已买队列
        long addResult = jedisUtil.sadd(hasBoughtSetKey, String.valueOf(user.getId()));
        if (addResult > 0) {
            //秒杀成功
            record=new Record();
            record.setCreateTime(new Date());
            record.setProduct(product);
            record.setState(1);
            record.setUser(user);
            //添加record到rabbitMQ消息队列
            mqUtil.send(JSON.toJSONString(record));
            //返回秒杀成功
            return SecKillEnum.SUCCESS;
        } else {
            //重复秒杀
            //这里抛出RuntimeException异常，redis的decr操作并不会回滚，所以需要手动incr回去
            jedisUtil.incrBy(productStockCacheKey, 1);
            throw new SecKillException(SecKillEnum.REPEAT);
        }
    }


}
