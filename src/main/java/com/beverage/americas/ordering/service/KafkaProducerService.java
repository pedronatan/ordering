package com.beverage.americas.ordering.service;

import com.beverage.americas.ordering.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final String TOPIC = "processed_orders";

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrder(Order order) {
        try {
            kafkaTemplate.send(TOPIC, order.getId(), order);
            System.out.println("Pedido enviado ao Kafka: " + order);
        } catch (Exception e) {
            System.err.println("Erro ao enviar pedido ao Kafka: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
