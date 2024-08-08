package com.hsenid.smartstock.dto.mapper;


import com.hsenid.smartstock.dto.request.ProductRequest;
import com.hsenid.smartstock.dto.response.ProductResponse;
import com.hsenid.smartstock.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse toProductResponse(Product product) {
        ProductResponse response = new ProductResponse();

        response.setName(product.getName());
        response.setStatus(product.getStatus());
        response.setShippingId(product.getShippingId());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setCategoryId(product.getCategoryId());
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
        product.setCategoryId(productRequest.getCategoryId());
        product.setSupplierId(productRequest.getSupplierId());
        return product;
    }
}
