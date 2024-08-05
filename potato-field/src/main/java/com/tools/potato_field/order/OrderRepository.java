package com.tools.potato_field.repository;

import com.tools.potato_field.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
