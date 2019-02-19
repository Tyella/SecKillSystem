package com.tyella.seckill.dao;

import com.tyella.seckill.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProductDao {

    @Select("SELECT * FORM product WHERE id=#{id}")
    Product getProductById(long id);

    @Select("SELECT * FROM product")
    List<Product> getAllProduct();

    @Update({"update product set stock=stock=1 where product=#{product} and stock>0"})
    boolean updatePessLockInMySQL(Product product);

    @Update({"update product set stock=#{stock},version=version+1 where id=#{id} AND version=#{version}"})
    boolean updatePosiLockInMySQL(Product product);

    //减库存
    @Update({"update product set stock=stock-1 where product=#{product} and stock>0"})
    boolean updateByAsynPattern(Product product);

}
