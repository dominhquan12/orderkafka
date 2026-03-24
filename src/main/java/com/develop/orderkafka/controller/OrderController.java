package com.develop.orderkafka.controller;

import com.develop.orderkafka.entity.Order;
import com.develop.orderkafka.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order create(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(
                request.getProductName(),
                request.getQuantity()
        );
    }

    @Data
    static class CreateOrderRequest {
        private String productName;
        private int quantity;
    }
}