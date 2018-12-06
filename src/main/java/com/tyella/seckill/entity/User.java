package com.tyella.seckill.entity;

/*
用户信息实体
 */
public class User{
    //用户id
    private long id;
    //用户名
    private String userName;
    //用户密码
    private String password;
    //用户手机号
    private long user_phone;

    public User(){

    }

    public User(long id) {
        this.id = id;
    }

    public User(long id, String userName, String password, long user_phone) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.user_phone = user_phone;
    }

    public long getId() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(long user_phone) {
        this.user_phone = user_phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", user_phone=" + user_phone +
                '}';
    }
}
