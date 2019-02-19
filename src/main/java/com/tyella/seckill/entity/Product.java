package com.tyella.seckill.entity;

import java.math.BigDecimal;
import java.util.Date;


public class Product{

    private int id;

    private String productName;

    private BigDecimal price;

    private int stock;

    private Date createTime;

    private int version;   //MySQL锁有用

    public Product() {
    }

    public Product(int id, String productName, BigDecimal price, int stock, Date createTime, int version) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.createTime = createTime;
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
