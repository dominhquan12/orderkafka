package com.develop.orderkafka.repo;

import com.develop.orderkafka.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
