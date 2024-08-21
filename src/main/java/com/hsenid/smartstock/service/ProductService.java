package com.hsenid.smartstock.service;

import com.hsenid.smartstock.dto.mapper.CategoryMapper;
import com.hsenid.smartstock.entity.Category;
import com.hsenid.smartstock.entity.Product;
import com.hsenid.smartstock.repository.CategoryRepo;
import com.hsenid.smartstock.repository.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
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
    public Product updateProduct(Product product) {
        return productRepo.save(product);  // Assuming you're using JPA/Hibernate
    }
    public long getOutOfStockProductCount() {
        try {
            List<Product> outOfStockProducts = productRepo.findByStatus("Out of Stock");
            return outOfStockProducts.size();
        } catch (Exception e) {
            logger.error("Error getting out of stock product count", e);
            throw e;
        }
    }
    public long onDelivery() {
        try {
            List<Product> outOfStockProducts = productRepo.findByStatus("On Delivery");
            return outOfStockProducts.size();
        } catch (Exception e) {
            logger.error("Error getting out of stock product count", e);
            throw e;
        }
    }
    public long getLowInStockProductCount() {
        try {
            List<Product> lowInStockProducts = productRepo.findAll().stream()
                    .filter(product -> product.getQuantityInStock() < product.getQytTObeLowStock())
                    .collect(Collectors.toList());
            return lowInStockProducts.size();
        } catch (Exception e) {
            logger.error("Error getting low in stock product count", e);
            throw e;
        }
    }
    public List<Product> getLowInStockProducts() {
        List<Product> allProducts = productRepo.findAll();
        return allProducts.stream()
                .filter(p -> p.getQuantityInStock() < p.getQytTObeLowStock())
                .collect(Collectors.toList());
    }
    public List<Product> onDeliveryProducts() {
        try {
            List<Product> onDeliveryProducts = productRepo.findByStatus("On Delivery");
            return onDeliveryProducts;
        } catch (Exception e) {
            logger.error("Error getting products on delivery", e);
            throw e;
        }
    }

}