package com.tyella.seckill.dao;

import com.tyella.seckill.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SuccessKilledDaoTest {

    @Autowired
    SuccessKilledDao successKilledDao;

    @Test
    public void testGetProductById() throws Exception{
        long id=1;
        long userphone=1357205378;
        Product product=successKilledDao.getProductById(id,userphone);
        System.out.println(product);
    }

    @Test
    public void testInsertSuccessKilledRecord() throws Exception{

    }

}