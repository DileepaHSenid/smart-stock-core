package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.dto.mapper.ProductMapper;
import com.hsenid.smartstock.dto.request.ProductRequest;
import com.hsenid.smartstock.dto.response.ProductResponse;
import com.hsenid.smartstock.entity.Product;
import com.hsenid.smartstock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> productOpt = productService.getProductById(id);
        return productOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest, @RequestParam String categoryId) {
        try {
            Product product = productMapper.toProductRequest(productRequest);
            Product createdProduct = productService.createProduct(product, categoryId);
            ProductResponse productResponse = productMapper.toProductResponse(createdProduct);
            return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
