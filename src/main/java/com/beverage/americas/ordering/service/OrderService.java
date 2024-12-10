package com.beverage.americas.ordering.service;


import com.beverage.americas.ordering.model.Order;
import com.beverage.americas.ordering.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order processOrder(Order order) {
        double totalValue = order.getOrderItems().stream()
                .mapToDouble(p -> p.getUnitPrice() * p.getQuantity())
                .sum();
        order.setTotalValue(totalValue);
        order.setStatus("processed");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
}
