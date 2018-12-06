package com.tyella.seckill.entity;

import java.util.Date;

/*
  秒杀成功明细信息
 */
public class SuccessKilled {
    //id
    private long id;
    //用户手机号
    private long user_phone;
    //秒杀状态标识
    private int state;
    //创建时间
    private Date createTime;

    private Product product;

    public SuccessKilled(long id) {
        this.id = id;
    }

    public SuccessKilled(long id, long user_phone, int state, Date createTime, Product product) {
        this.id = id;
        this.user_phone = user_phone;
        this.state = state;
        this.createTime = createTime;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(long user_phone) {
        this.user_phone = user_phone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "id=" + id +
                ", user_phone=" + user_phone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
