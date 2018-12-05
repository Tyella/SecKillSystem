package com.tyella.seckill.dao;

import java.util.List;
import com.tyella.seckill.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserDao {

    @Select("SELECT * FROM user WHERE id=#{id}")
    User getUserById(@Param("id") int id);


    @Select("SELECT * FROM user")
    List<User> getAllUsers();

}
