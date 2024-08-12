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
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

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

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequest productRequest) {
        try {
            Product product = productMapper.toProductRequest(productRequest);
            Product createdProduct = productService.createProduct(product);
            ProductResponse productResponse = productMapper.toProductResponse(createdProduct);
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

    // Delete Product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String id) {
        try {
            boolean isRemoved = productService.deleteProduct(id);
            if (isRemoved) {
                return ResponseEntity.ok(
                        ApiResponse.forStatus(StatusCode.S0000)
                                .withMessage("Product deleted successfully")
                );
            } else {
                return ResponseEntity.ok(
                        ApiResponse.forStatus(StatusCode.E4004)
                                .withMessage("Product not found")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage("An error occurred while deleting the product")
            );
        }
    }
}
