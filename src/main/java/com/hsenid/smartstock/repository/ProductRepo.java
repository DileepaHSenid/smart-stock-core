package com.hsenid.smartstock.repository;


import com.hsenid.smartstock.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product,String> {
    List<Product> findByStatus(String status);
    @Query("SELECT p FROM Product p WHERE p.QuantityInStock < p.QytTObeLowStock")
    List<Product> findLowInStockProducts();
}
