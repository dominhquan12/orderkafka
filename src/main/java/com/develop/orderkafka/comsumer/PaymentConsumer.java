package com.develop.orderkafka.comsumer;

import com.develop.orderkafka.entity.OrderStatus;
import com.develop.orderkafka.entity.Payment;
import com.develop.orderkafka.entity.PaymentStatus;
import com.develop.orderkafka.event.OrderCreatedEvent;
import com.develop.orderkafka.event.PaymentResultEvent;
import com.develop.orderkafka.producer.PaymentProducer;
import com.develop.orderkafka.repo.OrderRepository;
import com.develop.orderkafka.repo.PaymentRepository;
import com.develop.orderkafka.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentProducer paymentProducer;
    private final PaymentRepository paymentRepository;
    private final InventoryService inventoryService;
    private final OrderRepository orderRepository;
    private final Random random = new Random();

    @KafkaListener(topics = "order.created")
    public void handle(OrderCreatedEvent event) throws InterruptedException {

        System.out.println("Processing payment for order: " + event.getOrderId());

        if (!inventoryService.checkAndReserve(event)) {
            System.out.println("Inventory not enough → cancel order");

            orderRepository.findById(event.getOrderId()).ifPresent(o -> {
                o.setStatus(OrderStatus.FAILED_INVENTORY.name());
                orderRepository.save(o);
            });

            return;
        }

        // Check if already processed
        if (paymentRepository.findByOrderId(event.getOrderId()).isPresent()) {
            System.out.println("Skip already processed order: " + event.getOrderId());
            return;
        }

        int attempts = 0;
        boolean success = false;

        while (attempts < 3 && !success) {
            attempts++;
            Thread.sleep(1000 + random.nextInt(2000)); // simulate delay

            success = random.nextBoolean(); // simulate 50% fail

            if (!success) {
                System.out.println("Attempt " + attempts + " FAILED");
            }
        }

        if (!success) {
            inventoryService.release(event);
        }

        Payment payment = Payment.builder()
                .orderId(event.getOrderId())
                .status(success ? PaymentStatus.PAID.name() : PaymentStatus.FAILED.name())
                .attempt(attempts)
                .createdAt(Instant.now())
                .build();

        paymentRepository.save(payment);

        paymentProducer.sendPaymentResult(
                new PaymentResultEvent(event.getOrderId(), success ? PaymentStatus.PAID.name() : PaymentStatus.FAILED.name())
        );
    }
}