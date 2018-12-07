package com.tyella.seckill.service;

import com.tyella.seckill.SeckillApplication;
import com.tyella.seckill.common.SecKillEnum;
import com.tyella.seckill.dao.ProductDao;
import com.tyella.seckill.dao.UserDao;

import com.tyella.seckill.entity.Product;
import com.tyella.seckill.entity.SuccessKilled;
import com.tyella.seckill.entity.User;
import com.tyella.seckill.exception.SecKillException;
import com.tyella.seckill.redis.RedisPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SecKillService {

    public static final Logger log= LoggerFactory.getLogger(SecKillService.class);

    @Autowired
    RedisPool redisPool;

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    SecKillEnum secKillEnum;

    public SecKillEnum handleByRedisWatch(Map<String,Object> paramMap) {
        Jedis jedis = redisPool.getJedis();
        SuccessKilled successKilled;
        long userId = (long) paramMap.get("UserID");
        long productId = (long) paramMap.get("ProductId");
        User user = userDao.getUserById(userId);
        Product product=productDao.getProductById(productId);

        //key,用于redis监控
        String productStock=String.valueOf(product.getNumber());
        //秒杀商品的id
        String hasRepatedPurchase=String.valueOf(product.getId());

        //redis key:如果事务执行前key被其他命令改动，那么操作将被打断
        jedis.watch(productStock);

        //如果重复秒杀，则抛出异常
        boolean isBuy=jedis.sismember(hasRepatedPurchase,String.valueOf(user.getId()));
        if (isBuy) {
            log.error("用户"+user.getUserName()+"重复购买"+product.getProductName());
            throw new SecKillException(secKillEnum.REPEAT);
        }

        //若库存不足，则抛出库存不足异常
        String stock=jedis.get(productStock);
        if (Integer.parseInt(stock) <= 0) {
            log.error("商品："+product.getProductName()+"库存不足！");
            throw new SecKillException(secKillEnum.LOW_STOCKS);
        }

        //开启redis事务
        Transaction tx=jedis.multi();
        tx.decrBy(productStock,1);
        List<Object> list=tx.exec();

        //redis的WATCH监控被修改，商品秒杀失败
        //用redis来实现一个原子计数器
        if (list == null || list.isEmpty()){
            jedis.unwatch();
            log.error("商品"+product.getProductName()+"watch监控被修改，商品秒杀失败！");
            throw new SecKillException(secKillEnum.FAIL);
        }

        long addResult=jedis.sadd(hasRepatedPurchase,String.valueOf(user.getId()));

        if (addResult > 0) {
            successKilled=new SuccessKilled(1,user.getUser_phone(),secKillEnum.SUCCESS.getCode(),new Date(),product);

            //TODO
            //rabbitMQ相关代码待完成
            rabbitMQSender.send(JSON.toJSONString(successKilled));
            return SecKillEnum.SUCCESS;
        }
        else{
            //返回0，说明商品已经在redis中，此时应该手动执行会滚操作
            tx.incrBy(productStock,1);
            log.error("商品"+product.getProductName()+"重复秒杀！");
            throw new SecKillException(secKillEnum.REPEAT);
        }
    }

    //TODO
    //测试contributes不能显示的问题


}
