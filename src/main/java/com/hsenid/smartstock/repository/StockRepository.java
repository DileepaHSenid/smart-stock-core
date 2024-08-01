package com.hsenid.smartstock.repository;

import com.hsenid.smartstock.entity.Order;
import com.hsenid.smartstock.entity.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends MongoRepository<Stock,String> {
    List<Stock> findByProductId(Stock productId);
}

