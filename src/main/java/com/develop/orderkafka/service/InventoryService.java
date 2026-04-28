package com.develop.orderkafka.service;

import com.develop.orderkafka.entity.Inventory;
import com.develop.orderkafka.entity.Order;
import com.develop.orderkafka.entity.OrderStatus;
import com.develop.orderkafka.event.OrderCreatedEvent;
import com.develop.orderkafka.repo.InventoryRepository;
import com.develop.orderkafka.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InventoryService  {

    private final InventoryRepository inventoryRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public boolean checkAndReserve(OrderCreatedEvent event) {

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow();

        // 🔥 IDPOTENCY CHECK
        if (OrderStatus.INVENTORY_RESERVED.name().equals(order.getStatus())) {
            System.out.println("Inventory already reserved → skip: " + event.getOrderId());
            return true;
        }

        if (OrderStatus.FAILED_INVENTORY.name().equals(order.getStatus())) {
            System.out.println("Order already failed inventory → skip: " + event.getOrderId());
            return false;
        }

        Inventory inventory = inventoryRepository.findById(event.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + event.getProductId()));

        if (inventory.getStock() < event.getQuantity()) {
            order.setStatus(OrderStatus.FAILED_INVENTORY.name());
            orderRepository.save(order);
            return false;
        }

        // trừ tồn kho
        inventory.setStock(inventory.getStock() - event.getQuantity());
        inventoryRepository.save(inventory);

        // update order status
        order.setStatus(OrderStatus.INVENTORY_RESERVED.name());
        orderRepository.save(order);

        return true;
    }

    @Transactional
    public void release(OrderCreatedEvent event) {

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow();

        // nếu đã rollback rồi thì skip
        if (OrderStatus.PAYMENT_FAILED == OrderStatus.valueOf(order.getStatus())) {
            return;
        }

        Inventory inventory = inventoryRepository.findById(event.getProductId())
                .orElseThrow();

        inventory.setStock(inventory.getStock() + event.getQuantity());
        inventoryRepository.save(inventory);

        order.setStatus(OrderStatus.PAYMENT_FAILED.name());
        orderRepository.save(order);
    }
}