package com.develop.orderkafka.strategypattern.factory;

import com.develop.orderkafka.strategypattern.exception.BusinessException;
import com.develop.orderkafka.strategypattern.exception.ErrorCode;
import com.develop.orderkafka.strategypattern.service.PaymentStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PaymentFactory {

    private final Map<String, PaymentStrategy> strategyMap;

    public PaymentFactory(List<PaymentStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(
                        PaymentStrategy::getMethodName,
                        Function.identity()
                ));
    }

    public PaymentStrategy getStrategy(String method) {
        PaymentStrategy strategy = strategyMap.get(method);
        if (strategy == null) {
            throw new BusinessException(ErrorCode.PAYMENT_METHOD_NOT_SUPPORTED);
        }
        return strategy;
    }
}
