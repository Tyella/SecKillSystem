package com.tyella.seckill.dao;

import com.tyella.seckill.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserDaoTest{

    @Resource
    private UserDao userDao;

    @Test
    public void testGetUserById() throws Exception{
        /*int id=0;
        User user=userDao.getUserById(id);
        System.out.println(user.getUser_phone());
        System.out.println(user);*/
    }

}