package com.develop.orderkafka.producer;

import com.develop.orderkafka.event.PaymentResultEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendPaymentResult(PaymentResultEvent event) {
        kafkaTemplate.send("payment.result", event);
    }
}