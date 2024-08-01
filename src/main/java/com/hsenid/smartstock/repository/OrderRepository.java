package com.hsenid.smartstock.repository;

import com.hsenid.smartstock.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository <Order,String> {
    List<Order> findByProductId(String productId);
    List<Order> findBySupplierId(String supplierId);
}