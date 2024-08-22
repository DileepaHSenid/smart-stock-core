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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

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
            // Check if product with the same name already exists
            if (productService.productExistsByName(productRequest.getName())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(ApiResponse.forStatus(StatusCode.E4001)
                                .withMessage("Product with the name '" + productRequest.getName() + "' already exists."));
            }

            // Proceed to create the product
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


    @DeleteMapping("/delete/{id}")
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

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest) {
        try {
            Optional<Product> existingProductOpt = productService.getProductById(id);
            if (existingProductOpt.isPresent()) {
                Product existingProduct = existingProductOpt.get();

                // Check if another product with the same name already exists
                if (productService.productExistsByNameAndDifferentId(productRequest.getName(), id)) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(ApiResponse.forStatus(StatusCode.E4001)
                                    .withMessage("Product with the name '" + productRequest.getName() + "' already exists."));
                }

                productMapper.updateProductFromRequest(productRequest, existingProduct);
                Product updatedProduct = productService.updateProduct(existingProduct);
                ProductResponse productResponse = productMapper.toProductResponse(updatedProduct);

                return ResponseEntity.ok(
                        ApiResponse.forStatus(StatusCode.S0000)
                                .withMessage("Product updated successfully")
                                .withPayload(productResponse)
                );
            } else {
                return ResponseEntity.ok(
                        ApiResponse.forStatus(StatusCode.E4004)
                                .withMessage("Product not found")
                );
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E4001)
                            .withMessage(StatusCode.E4001.getMessage())
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage("An error occurred while updating the product")
            );
        }
    }


    @GetMapping("/out-of-stock/count")
    public ResponseEntity<ApiResponse> getOutOfStockProductCount() {
        try {
            long count = productService.getOutOfStockProductCount();
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage("Out of stock product count retrieved successfully")
                            .withPayload(count)
            );
        } catch (Exception e) {
            logger.error("Error in getOutOfStockProductCount", e);
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage("An error occurred while retrieving out of stock product count: ".concat(e.getMessage()))
            );
        }
    }

    @GetMapping("/onDelivery/count")
    public ResponseEntity<ApiResponse> getOnDeliveryCount() {
        try {
            long count = productService.onDelivery();
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage("Out of stock product count retrieved successfully")
                            .withPayload(count)
            );
        } catch (Exception e) {
            logger.error("Error in getOutOfStockProductCount", e);
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage("An error occurred while retrieving out of stock product count: " + e.getMessage())
            );
        }
    }

    @GetMapping("/low-in-stock/count")
    public ResponseEntity<ApiResponse> getLowInStockProductCount() {
        try {
            long count = productService.getLowInStockProductCount();
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage("Low in stock product count retrieved successfully")
                            .withPayload(count)
            );
        } catch (Exception e) {
            logger.error("Error in getLowInStockProductCount", e);
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage("An error occurred while retrieving low in stock product count: " + e.getMessage())
            );
        }
    }

    @GetMapping("/low-in-stock")
    public ResponseEntity<ApiResponse> getLowStockProducts() {
        try {
            List<ProductResponse> lowInStockProducts = productService.getLowInStockProducts()
                    .stream()
                    .map(productMapper::toProductResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage("Low in stock products retrieved successfully")
                            .withPayload(lowInStockProducts)
            );
        } catch (Exception e) {
            logger.error("Error in getLowStockProducts", e);
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage("An error occurred while retrieving low in stock products" + e.getMessage())
            );
        }
    }
    @GetMapping("/on-delivery")
    public ResponseEntity<ApiResponse> getOnDeliveryProducts() {
        try {
            List<Product> onDeliveryProducts = productService.onDeliveryProducts();
            List<ProductResponse> productResponses = onDeliveryProducts.stream()
                    .map(productMapper::toProductResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage("Products on delivery retrieved successfully")
                            .withPayload(productResponses)
            );
        } catch (Exception e) {
            logger.error("Error retrieving products on delivery", e);
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage("An error occurred while retrieving products on delivery: " + e.getMessage())
            );
        }
    }

}
