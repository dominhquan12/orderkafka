package com.develop.orderkafka.entity;

public enum OrderStatus {
    CREATED,
    INVENTORY_RESERVED,
    PAYMENT_PROCESSING,
    PAID,
    PAYMENT_FAILED,
    FAILED_INVENTORY
}