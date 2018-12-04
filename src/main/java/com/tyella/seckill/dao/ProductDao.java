package com.tyella.seckill.dao;

import com.tyella.seckill.entity.Product;

import java.util.Date;
import java.util.List;

public interface ProductDao {

    Product getProductById(long id);

    List<Product> getAllProduct();

    //减库存
    int reduceNumber(long id, Date killTime);
}
