package com.develop.orderkafka.strategypattern.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentResult {
    private String paymentMethod;
    private Double amount;
}
