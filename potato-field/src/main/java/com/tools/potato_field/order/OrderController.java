package com.tools.potato_field.controller;

import com.tools.potato_field.entity.Order;
import com.tools.potato_field.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.findOrder(id);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAllOrders();
    }
}