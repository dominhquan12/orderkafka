package com.develop.orderkafka.comsumer;

import com.develop.orderkafka.entity.Order;
import com.develop.orderkafka.event.PaymentResultEvent;
import com.develop.orderkafka.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "payment.result")
    public void handle(PaymentResultEvent event) {

        System.out.println("Updating order: " + event.getOrderId());

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow();

        order.setStatus(event.getStatus());

        orderRepository.save(order);
    }
}