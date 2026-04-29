package com.develop.orderkafka.strategypattern.controller;

import com.develop.orderkafka.strategypattern.dto.PaymentRequest;
import com.develop.orderkafka.strategypattern.dto.PaymentResult;
import com.develop.orderkafka.strategypattern.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResult> pay(@RequestBody PaymentRequest request) {
        PaymentResult result = paymentService.pay(request);
        return ResponseEntity.ok(result);
    }
}