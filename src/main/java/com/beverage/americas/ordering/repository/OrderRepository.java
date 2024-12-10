package com.beverage.americas.ordering.repository;

import com.beverage.americas.ordering.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByStatus(String status);
}
