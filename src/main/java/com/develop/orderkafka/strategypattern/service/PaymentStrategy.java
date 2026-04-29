package com.develop.orderkafka.strategypattern.service;

import com.develop.orderkafka.strategypattern.dto.PaymentResult;

public interface PaymentStrategy {
    PaymentResult pay(double amount);
    String getMethodName();
}