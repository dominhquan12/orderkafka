package com.develop.orderkafka.producer;

import com.develop.orderkafka.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrderCreated(OrderCreatedEvent event) {
        kafkaTemplate.send("order.created", event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("Order event sent: " + event);
                    } else {
                        System.err.println("Failed to send order event: " + ex.getMessage());
                    }
                });
    }
}