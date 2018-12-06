package com.tyella.seckill.common;

public enum SecKillEnum {

    SUCCESS(1,"秒杀成功"),
    FAIL(0,"秒杀失败"),
    REPEAT(-1,"重复秒杀"),
    LOW_STOCKS(-1,"库存不足"),
    SYSTEM_EXCEPTION(-2,"系统异常");


    private int code;

    private String message;

    SecKillEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
