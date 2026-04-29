package com.develop.orderkafka.strategypattern.exception;

public enum ErrorCode {

    PAYMENT_METHOD_NOT_SUPPORTED("PAY_001", "error.payment.method.not.supported"),
    AMOUNT_MUST_BE_POSITIVE("PAY_002", "error.amount.must.be.positive"),
    AMOUNT_MIN("PAY_003", "error.amount.min"),
    AMOUNT_MAX("PAY_004", "error.amount.max");

    private final String code;
    private final String messageKey;

    ErrorCode(String code, String messageKey) {
        this.code = code;
        this.messageKey = messageKey;
    }

    public String getCode() { return code; }
    public String getMessageKey() { return messageKey; }
}