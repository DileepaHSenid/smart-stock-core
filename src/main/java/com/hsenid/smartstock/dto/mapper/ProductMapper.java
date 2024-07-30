package com.hsenid.smartstock.dto.mapper;


import com.hsenid.smartstock.dto.request.ProductRequest;
import com.hsenid.smartstock.entity.Product;

public class ProductMapper {
    public ProductRequest toProductResponse(Product product) {
        ProductRequest response = new ProductRequest();
        response.setName(product.getName());
        response.setStatus(product.getStatus());
        response.setShippingId(product.getShippingId());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setCategory(product.getCategory());
        response.setSupplierId(product.getSupplierId());
        return response;
    }

    public Product toProductRequest(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setStatus(productRequest.getStatus());
        product.setShippingId(productRequest.getShippingId());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setSupplierId(productRequest.getSupplierId());
        return product;
    }
}
