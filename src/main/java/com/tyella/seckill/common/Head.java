package com.tyella.seckill.common;

/*
消息头，包含状态码及状态描述
 */
public class Head {

    //状态码
    private String stateCode;

    private String stateMessage;

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}
