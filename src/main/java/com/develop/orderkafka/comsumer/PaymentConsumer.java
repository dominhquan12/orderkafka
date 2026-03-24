package com.develop.orderkafka.consumer;

import com.develop.orderkafka.event.OrderCreatedEvent;
import com.develop.orderkafka.event.PaymentResultEvent;
import com.develop.orderkafka.producer.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentProducer paymentProducer;
    private final Random random = new Random();

    @KafkaListener(topics = "order.created")
    public void handle(OrderCreatedEvent event) {

        System.out.println("Processing payment for order: " + event.getOrderId());

        boolean success = random.nextBoolean();

        String status = success ? "PAID" : "FAILED";

        PaymentResultEvent resultEvent =
                new PaymentResultEvent(event.getOrderId(), status);

        paymentProducer.sendPaymentResult(resultEvent);
    }
}