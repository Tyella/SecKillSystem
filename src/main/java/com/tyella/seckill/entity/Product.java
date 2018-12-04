package com.tyella.seckill.entity;

import java.util.Date;

/*
商品实体信息
 */
public class Product{

    //商品id
    private long id;
    //商品名称
    private String productName;
    //库存数量
    private int number;
    //秒杀开启时间
    private Date start_time;
    //秒杀结束时间
    private Date end_time;
    //订单创建时间
    private Date create_time;

    public Product(long id) {
        this.id = id;
    }

    public Product(long id, String productName, int number, Date start_time, Date end_time, Date create_time) {
        this.id = id;
        this.productName = productName;
        this.number = number;
        this.start_time = start_time;
        this.end_time = end_time;
        this.create_time = create_time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", number=" + number +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", create_time=" + create_time +
                '}';
    }
}
