package com.tyella.seckill.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tyella.seckill.dao.ProductDao;
import com.tyella.seckill.dao.RecordDao;
import com.tyella.seckill.entity.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RabbitListener(queues = "seckillQueue")
public class MQUtil implements  RabbitTemplate.ConfirmCallback{
    private static final Logger logger= LoggerFactory.getLogger(MQUtil.class);

    @Autowired
    private ProductDao productDao;

    @Autowired
    private RecordDao recordDao;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RabbitHandler
    public void process(String message) throws Exception {
        Record record = JSON.parseObject(message, new TypeReference<Record>() {
        });
        //插入record
        recordDao.insertRecord(record);
        //更改物品库存
        productDao.updateByAsynPattern(record.getProduct());
    }

    public void send(String message) {
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("seckillExchange", "seckillRoutingKey", message, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("callbakck confirm: " + correlationData.getId());
        if (ack){
            logger.info("插入record成功，更改库存成功");
        }else{
            logger.info("cause:"+cause);
        }
    }

}
