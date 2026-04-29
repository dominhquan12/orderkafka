package com.develop.orderkafka.strategypattern.strategy;

import com.develop.orderkafka.strategypattern.domain.PaymentMethod;
import com.develop.orderkafka.strategypattern.dto.PaymentResult;

public interface PaymentStrategy {
    PaymentResult pay(double amount);

    PaymentMethod getPaymentMethod();
}