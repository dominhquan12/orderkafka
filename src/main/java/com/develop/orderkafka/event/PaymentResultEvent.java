package com.develop.orderkafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResultEvent {

    private Long orderId;
    private String status; // PAID hoặc FAILED
}