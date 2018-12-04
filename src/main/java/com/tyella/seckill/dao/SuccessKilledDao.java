package com.tyella.seckill.dao;

import com.tyella.seckill.entity.Product;

public interface SuccessKilledDao {

    //插入购买明细，可过滤重复
    int insertSuccessKilledRecord(long id,long userPhone);

    Product getProductById(long id,long userPhone);
}
