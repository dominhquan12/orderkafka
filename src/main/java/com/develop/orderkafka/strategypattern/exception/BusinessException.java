package com.develop.orderkafka.strategypattern.exception;

public class BusinessException extends RuntimeException {

    private final String errorCode;

    public BusinessException(ErrorCode errorCode, Object... args) {
        super(String.format(errorCode.getMessage(), args));
        this.errorCode = errorCode.getCode();
    }

    public String getErrorCode() {
        return errorCode;
    }
}