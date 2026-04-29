package com.develop.orderkafka.strategypattern.service;

import com.develop.orderkafka.strategypattern.dto.PaymentResult;
import com.develop.orderkafka.strategypattern.exception.BusinessException;
import com.develop.orderkafka.strategypattern.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class ZaloPayPayment implements PaymentStrategy {

    @Override
    public PaymentResult pay(double amount) {
        if (amount < 10_000) {
            throw new BusinessException(ErrorCode.AMOUNT_MUST_BE_GREATER_THAN_OR_EQUAL, 10_000);
        }

        if (amount > 1000_000) {
            throw new BusinessException(ErrorCode.AMOUNT_MUST_BE_GREATER_THAN_OR_EQUAL, 1000_000);
        }
        return PaymentResult.builder()
                .paymentMethod(getMethodName())
                .amount(amount)
                .build();
    }

    @Override
    public String getMethodName() {
        return "ZALO_PAY";
    }
}