package com.beverage.americas.ordering.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "orders_items")
public class OrderItem {
    private String id;
    private Integer quantity;
    private Double unitPrice;
}
