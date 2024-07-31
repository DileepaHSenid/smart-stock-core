package com.hsenid.smartstock.service;

import com.hsenid.smartstock.entity.Product;
import com.hsenid.smartstock.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product createProduct(Product product) {

        return productRepo.save(product);
    }

}
