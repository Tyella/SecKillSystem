package com.tyella.seckill.dao;

import com.tyella.seckill.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

@Mapper
public interface ProductDao {

    @Select("SELECT * FORM product WHERE id=#{id}")
    Product getProductById(long id);

    @Select("SELECT * FROM product")
    List<Product> getAllProduct();

    //减库存
    @Update("UPDATE product WHERE id=#{id} AND number>0 AND killtime>start_time AND killtime<=end_time")
    int reduceNumber(long id, Date killTime);
}
