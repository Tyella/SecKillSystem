package com.tyella.seckill.entity;

import java.util.Date;

/**
 * 用户信息
 */
public class User{

    private int id;

    private String userName;

    private String phone;        //电话号

    private Date createTime;     //创建时间

    public User() {
    }

    public User(int id, String userName, String phone, Date createTime) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
