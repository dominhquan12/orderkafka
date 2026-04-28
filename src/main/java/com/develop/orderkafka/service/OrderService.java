package com.develop.orderkafka.service;

import com.develop.orderkafka.entity.Order;
import com.develop.orderkafka.entity.OrderStatus;
import com.develop.orderkafka.event.OrderCreatedEvent;
import com.develop.orderkafka.producer.OrderProducer;
import com.develop.orderkafka.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.CREATED.name());
        Order saved = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                saved.getId(),
                saved.getProductId(),
                saved.getProductName(),
                saved.getQuantity()
        );

        orderProducer.sendOrderCreated(event);

        return saved;
    }
}