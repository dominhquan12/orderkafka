package com.develop.orderkafka.strategypattern.strategy;

import com.develop.orderkafka.strategypattern.dto.PaymentResult;
import com.develop.orderkafka.strategypattern.exception.BusinessException;
import com.develop.orderkafka.strategypattern.exception.ErrorCode;
import com.develop.orderkafka.strategypattern.domain.PaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPayment implements PaymentStrategy {

    public PaymentResult pay(double amount) {
        if (amount < 20_000) {
            throw new BusinessException(ErrorCode.AMOUNT_MIN, 20_000);
        }

        if (amount > 2000_000) {
            throw new BusinessException(ErrorCode.AMOUNT_MAX, 2000_000);
        }
        return PaymentResult.builder()
                .paymentMethod(getPaymentMethod())
                .amount(amount)
                .build();
    }

    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.CREDIT_CARD;
    }
}