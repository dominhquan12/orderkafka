package com.develop.orderkafka.strategypattern.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "{error.name.required}")
    private String name;

    @Email(message = "{error.email.invalid}")
    @NotBlank(message = "{error.email.required}")
    private String email;

    @Min(value = 18, message = "{error.age.min}")
    private Integer age;

    @NotBlank(message = "{error.password.required}")
    @Size(min = 6, max = 20, message = "{error.password.size}")
     private String password;

    @NotBlank(message = "{error.phone.required}")
    @Pattern(regexp = "^[0-9]+$", message = "{error.phone.invalid}")
    private String phone;
}
