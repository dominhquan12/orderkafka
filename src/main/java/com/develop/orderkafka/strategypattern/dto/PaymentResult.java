package com.develop.orderkafka.strategypattern.dto;

import com.develop.orderkafka.strategypattern.domain.PaymentMethod;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentResult {
    private PaymentMethod paymentMethod;
    private Double amount;
}
