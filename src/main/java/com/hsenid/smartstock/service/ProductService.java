package com.hsenid.smartstock.service;

import com.hsenid.smartstock.dto.mapper.CategoryMapper;
import com.hsenid.smartstock.entity.Category;
import com.hsenid.smartstock.entity.Product;
import com.hsenid.smartstock.repository.CategoryRepo;
import com.hsenid.smartstock.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryMapper categoryMapper;


    public List<Product> getAllProducts() {

        return productRepo.findAll();
    }

    public Product createProduct(Product product) {

            return productRepo.save(product);
        }

    public boolean deleteProduct(String productId) {
        if (productRepo.existsById(productId)) {
            productRepo.deleteById(productId);
            return true;
        }
        return false;
    }

    public Optional<Product> getProductById(String id) {
        return productRepo.findById(id);
    }
}