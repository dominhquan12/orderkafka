package com.develop.orderkafka.strategypattern.service;

import com.develop.orderkafka.strategypattern.dto.PaymentRequest;
import com.develop.orderkafka.strategypattern.dto.PaymentResult;
import com.develop.orderkafka.strategypattern.exception.BusinessException;
import com.develop.orderkafka.strategypattern.exception.ErrorCode;
import com.develop.orderkafka.strategypattern.factory.PaymentFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentFactory paymentFactory;

    public PaymentResult pay(PaymentRequest paymentRequest) {
        if (paymentRequest.getAmount() <= 0) {
            throw new BusinessException(ErrorCode.AMOUNT_MUST_BE_POSITIVE);
        }

        PaymentStrategy strategy = paymentFactory.getStrategy(paymentRequest.getPaymentMethod());
        return strategy.pay(paymentRequest.getAmount());
    }
}
