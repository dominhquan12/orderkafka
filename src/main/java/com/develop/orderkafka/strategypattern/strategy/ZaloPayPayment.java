package com.develop.orderkafka.strategypattern.strategy;

import com.develop.orderkafka.strategypattern.dto.PaymentResult;
import com.develop.orderkafka.strategypattern.exception.BusinessException;
import com.develop.orderkafka.strategypattern.exception.ErrorCode;
import com.develop.orderkafka.strategypattern.domain.PaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class ZaloPayPayment implements PaymentStrategy {

    @Override
    public PaymentResult pay(double amount) {
        if (amount < 10_000) {
            throw new BusinessException(ErrorCode.AMOUNT_MIN, 10_000);
        }

        if (amount > 1000_000) {
            throw new BusinessException(ErrorCode.AMOUNT_MAX, 1000_000);
        }
        return PaymentResult.builder()
                .paymentMethod(getPaymentMethod())
                .amount(amount)
                .build();
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.ZALO_PAY;
    }
}