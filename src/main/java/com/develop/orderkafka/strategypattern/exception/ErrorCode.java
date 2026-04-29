package com.develop.orderkafka.strategypattern.exception;

public enum ErrorCode {

    PAYMENT_METHOD_NOT_SUPPORTED("PAY_001", "Unsupported payment method"),
    AMOUNT_MUST_BE_POSITIVE("PAY_002", "Amount must be positive"),
    AMOUNT_MUST_BE_GREATER_THAN_OR_EQUAL("PAY_003", "Amount must be greater than or equal to %s"),
    AMOUNT_MUST_BE_LESS_THAN_OR_EQUAL("PAY_004", "Amount must be less than or equal to %s"),
    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
}