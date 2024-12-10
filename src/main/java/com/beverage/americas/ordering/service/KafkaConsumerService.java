package com.beverage.americas.ordering.service;

import com.beverage.americas.ordering.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "incoming_orders", groupId = "order_service_group")
    public void receiveOrder(Order order) {
        orderService.processOrder(order);
    }
}
