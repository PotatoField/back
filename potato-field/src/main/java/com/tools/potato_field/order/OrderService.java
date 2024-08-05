package com.tools.potato_field.service;

import com.tools.potato_field.entity.Order;
import com.tools.potato_field.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order findOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}