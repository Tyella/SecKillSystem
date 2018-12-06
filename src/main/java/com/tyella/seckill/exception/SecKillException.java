package com.tyella.seckill.exception;

import com.tyella.seckill.common.SecKillEnum;

//继承了java运行期异常
//TODO
public class SecKillException extends RuntimeException {

    private SecKillEnum secKillEnum;

    public SecKillException(SecKillEnum secKillEnum) {
        this.secKillEnum = secKillEnum;
    }

    public SecKillEnum getSecKillEnum() {
        return secKillEnum;
    }

    public void setSecKillEnum(SecKillEnum secKillEnum) {
        this.secKillEnum = secKillEnum;
    }
}
