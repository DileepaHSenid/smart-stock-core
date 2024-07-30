package com.IMS.IMS.repository;

import com.IMS.IMS.model.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.*;
import java.util.Optional;

public interface SupplierRepo extends MongoRepository <Supplier,String> {

}
