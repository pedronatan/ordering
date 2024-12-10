package com.beverage.americas.ordering;

import com.beverage.americas.ordering.model.Order;
import com.beverage.americas.ordering.model.OrderItem;
import com.beverage.americas.ordering.repository.OrderRepository;
import com.beverage.americas.ordering.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import scala.Product;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processOrder_shouldCalculateTotalAndSaveOrder() {
        Order order = new Order();
        order.setOrderItems(Arrays.asList(
                new OrderItem("item1", 2, 50.0),
                new OrderItem("item2", 1, 100.0)
        ));

        Order savedOrder = new Order();
        savedOrder.setId("order123");
        savedOrder.setOrderItems(order.getOrderItems());
        savedOrder.setTotalValue(200.0);
        savedOrder.setStatus("processed");
        savedOrder.setCreatedAt(LocalDateTime.now());
        savedOrder.setUpdatedAt(LocalDateTime.now());

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        Order result = orderService.processOrder(order);

        assertNotNull(result);
        assertEquals(200.0, result.getTotalValue());
        assertEquals("processed", result.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

}
