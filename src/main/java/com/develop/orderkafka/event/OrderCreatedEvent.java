package com.develop.orderkafka.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private Long orderId;
    private Long productId;
    private String productName;
    private int quantity;
}