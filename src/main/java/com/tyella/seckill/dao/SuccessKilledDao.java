package com.tyella.seckill.dao;

import com.tyella.seckill.entity.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SuccessKilledDao {

    //插入购买明细，可过滤重复
    @Insert("INSERT INTO success_killed(id,user_phone) VALUES(#{id},#{user_phone}")
    int insertSuccessKilledRecord(long id,long userPhone);

    @Select("SELECT * FROM success_killed WHERE id=#{id} AND user_phone=#{user_phone}")
    Product getProductById(long id,long user_phone);
}
