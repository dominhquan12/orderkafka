package com.develop.orderkafka.strategypattern.dto;

import com.develop.orderkafka.strategypattern.domain.PaymentMethod;
import lombok.Data;

@Data
public class PaymentRequest {
    private PaymentMethod paymentMethod;
    private Double amount;
}
