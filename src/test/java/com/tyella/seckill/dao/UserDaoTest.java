package com.tyella.seckill.dao;

import com.tyella.seckill.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserDaoTest{

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetUserById() throws Exception{
        int id=0;
        User user=userDao.getUserById(id);
        System.out.println(user.getUser_phone());
        System.out.println(user);
    }

    @Test
    public void testGetAllUsers() throws Exception{
        List<User> user=userDao.getAllUsers();
        System.out.println(user);
    }

}