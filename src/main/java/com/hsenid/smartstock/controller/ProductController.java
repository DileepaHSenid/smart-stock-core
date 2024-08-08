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
@RequestMapping
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest, @RequestParam(required = false) String categoryId) {
        try {
            Product product = productMapper.toProductRequest(productRequest);
            Product createdProduct = productService.createProduct(product, categoryId);
            ProductResponse productResponse = productMapper.toProductResponse(createdProduct);
            return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
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

<<<<<<< Updated upstream

=======
    @PostMapping("/create/{categoryId}")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest, @PathVariable String categoryId) {
        try {
            Product product = productMapper.toProduct(productRequest);
            product.setCategoryId(categoryId);
            Product createdProduct = productService.createProduct(product);
            ProductResponse productResponse = productMapper.toProductResponse(createdProduct);
            return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // This will log the full stack trace
            return ResponseEntity.badRequest().body(null);
        }
    }
>>>>>>> Stashed changes
}
