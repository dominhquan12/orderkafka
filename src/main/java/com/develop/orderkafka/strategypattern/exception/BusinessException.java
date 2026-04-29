package com.develop.orderkafka.strategypattern.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final String errorCode;
    private final String messageKey;
    private final Object[] args;

    public BusinessException(ErrorCode errorCode, Object... args) {
        this.errorCode = errorCode.getCode();
        this.messageKey = errorCode.getMessageKey();
        this.args = args;
    }
}