package com.develop.orderkafka.strategypattern.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String paymentMethod;
    private Double amount;
}
