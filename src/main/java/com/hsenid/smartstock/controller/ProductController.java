package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.common.ApiResponse;
import com.hsenid.smartstock.common.StatusCode;
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
    public ResponseEntity<ApiResponse> getProductById(@PathVariable String id) {
        try {
            Optional<Product> productOpt = productService.getProductById(id);
            if (productOpt.isPresent()) {
                Product product = productOpt.get();
                ProductResponse productResponse = productMapper.toProductResponse(product);
                return ResponseEntity.ok(
                        ApiResponse.forStatus(StatusCode.S0000)
                                .withMessage(StatusCode.S0000.getMessage())
                                .withPayload(productResponse)
                );
            } else {
                return ResponseEntity.ok(
                        ApiResponse.forStatus(StatusCode.E4004)
                                .withMessage(StatusCode.E4004.getMessage())
                );
            }
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage(StatusCode.E5000.getMessage())
            );
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<ProductResponse> products = productService.getAllProducts()
                    .stream()
                    .map(productMapper::toProductResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage(StatusCode.S0000.getMessage())
                            .withPayload(products)
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage(StatusCode.E5000.getMessage())
            );
        }
    }


    @PostMapping("/create/{categoryId}")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest, @PathVariable String categoryId) {

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequest productRequest, @RequestParam String categoryId) {

        try {
            Product product = productMapper.toProduct(productRequest);
            product.setCategoryId(categoryId);
            Product createdProduct = productService.createProduct(product);
            ProductResponse productResponse = productMapper.toProductResponse(createdProduct);

            return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // This will log the full stack trace
            return ResponseEntity.badRequest().body(null);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage(StatusCode.S0000.getMessage())
                            .withPayload(productResponse));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E4001)
                            .withMessage(StatusCode.E4001.getMessage())
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage(StatusCode.E5000.getMessage())
            );

        }
    }