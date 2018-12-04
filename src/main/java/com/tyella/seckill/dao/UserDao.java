package com.tyella.seckill.dao;

import java.util.List;
import com.tyella.seckill.entity.User;

public interface UserDao {

    User getUserById(int id);

    List<User> getAllUsers();

}
