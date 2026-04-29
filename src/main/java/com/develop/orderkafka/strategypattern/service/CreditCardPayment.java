package com.develop.orderkafka.strategypattern.service;

import com.develop.orderkafka.strategypattern.dto.PaymentResult;
import com.develop.orderkafka.strategypattern.exception.BusinessException;
import com.develop.orderkafka.strategypattern.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPayment implements PaymentStrategy {

    public PaymentResult pay(double amount) {
        if (amount < 20_000) {
            throw new BusinessException(ErrorCode.AMOUNT_MUST_BE_GREATER_THAN_OR_EQUAL, 20_000);
        }

        if (amount > 2000_000) {
            throw new BusinessException(ErrorCode.AMOUNT_MUST_BE_GREATER_THAN_OR_EQUAL, 2000_000);
        }
        return PaymentResult.builder()
                .paymentMethod(getMethodName())
                .amount(amount)
                .build();
    }

    public String getMethodName() {
        return "CREDIT_CARD";
    }
}