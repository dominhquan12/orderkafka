package com.develop.orderkafka.consumer;

import com.develop.orderkafka.event.OrderCreatedEvent;
import com.develop.orderkafka.event.PaymentResultEvent;
import com.develop.orderkafka.producer.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentProducer paymentProducer;
    private final Random random = new Random();

    @KafkaListener(topics = "order.created")
    public void handle(OrderCreatedEvent event) throws InterruptedException {

        System.out.println("Processing payment for order: " + event.getOrderId());

        int attempts = 0;
        boolean success = false;

        while (attempts < 3 && !success) {
            attempts++;

            Thread.sleep(2000);

            success = random.nextBoolean();

            if (!success) {
                System.out.println("Attempt " + attempts + " FAILED");
            }
        }

        if (!success) {
            System.out.println("💀 Payment failed after retries");

            paymentProducer.sendPaymentResult(
                    new PaymentResultEvent(event.getOrderId(), "FAILED")
            );

            return; // ❗ KHÔNG throw
        }

        System.out.println("Payment SUCCESS");

        paymentProducer.sendPaymentResult(
                new PaymentResultEvent(event.getOrderId(), "PAID")
        );
    }
}