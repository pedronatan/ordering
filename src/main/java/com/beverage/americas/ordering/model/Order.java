package com.beverage.americas.ordering.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private List<OrderItem> orderItems;
    private Double totalValue;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}