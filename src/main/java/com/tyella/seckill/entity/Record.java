package com.tyella.seckill.entity;

import java.util.Date;

/**
 * 购买明细
 */
public class Record {

    private int id;

    private Product product;

    private User user;

    private Date createTime;

    /**
     *  1：秒杀成功 2：秒杀失败  3：重复秒杀 4：库存不足 5：系统异常
     */
    private int state;

    private String stateInfo;

    public Record() {
    }

    public Record(int id, Product product, User user, Date createTime, int state, String stateInfo) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.createTime = createTime;
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
