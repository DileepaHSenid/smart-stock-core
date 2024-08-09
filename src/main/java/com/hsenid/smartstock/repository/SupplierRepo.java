package com.hsenid.smartstock.repository;


import com.hsenid.smartstock.entity.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplierRepo extends MongoRepository<Supplier,String> {

}
