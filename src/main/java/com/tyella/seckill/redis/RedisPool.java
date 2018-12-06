package com.tyella.seckill.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisPool {

    @Autowired
    JedisPool jedisPool;

    public Jedis getJedis(){
        return jedisPool.getResource();
    }
}
