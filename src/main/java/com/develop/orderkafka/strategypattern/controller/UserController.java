package com.develop.orderkafka.strategypattern.controller;

import com.develop.orderkafka.strategypattern.dto.UserRequest;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public String createUser(@Valid @RequestBody UserRequest request) {
        return "User created successfully";
    }
}