package com.beverage.americas.ordering;

import com.beverage.americas.ordering.model.Order;
import com.beverage.americas.ordering.service.KafkaProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class KafkaProducerServiceTest {
    @Mock
    private KafkaTemplate<String, Order> kafkaTemplate;

    @InjectMocks
    private KafkaProducerService kafkaProducerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendOrder_shouldSendMessageToKafka() {
        Order order = new Order();
        order.setId("order123");

        kafkaProducerService.sendOrder(order);

        verify(kafkaTemplate, times(1)).send(eq("processed_orders"), eq("order123"), eq(order));
    }
}
