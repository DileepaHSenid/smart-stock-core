package com.hsenid.smartstock.repository;


import com.hsenid.smartstock.entity.Product;
import com.hsenid.smartstock.entity.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product,String> {
}
