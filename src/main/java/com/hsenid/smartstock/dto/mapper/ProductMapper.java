package com.hsenid.smartstock.dto.mapper;


import com.hsenid.smartstock.dto.request.ProductRequest;
import com.hsenid.smartstock.dto.response.ProductResponse;
import com.hsenid.smartstock.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse toProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setImg(product.getImg());
        response.setName(product.getName());
        response.setStatus(product.getStatus());
        response.setShippingId(product.getShippingId());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantityInStock(product.getQuantityInStock());
        response.setQuantityToReceive(product.getQuantityToReceive());
        response.setLastOrderedDate(product.getLastOrderedDate());
        response.setCategoryId(product.getCategoryId());
        response.setSupplierFirstName(product.getSupplierFirstName());
        response.setSupplierLastName(product.getSupplierLastName());
        response.setShippingId(product.getSupplierID());
        return response;
    }

    public Product toProductRequest(ProductRequest productRequest) {
        Product product = new Product();
        product.setImg(productRequest.getImg());
        product.setName(productRequest.getName());
        product.setStatus(productRequest.getStatus());
        product.setShippingId(productRequest.getShippingId());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantityInStock(productRequest.getQuantityInStock());
        product.setQuantityToReceive(productRequest.getQuantityToReceive());
        product.setCategoryId(productRequest.getCategoryId());
        product.setSupplierFirstName(productRequest.getSupplierFirstName());
        product.setSupplierLastName(productRequest.getSupplierLastName());
        product.setLastOrderedDate(productRequest.getLastOrderedDate());
        product.setSupplierID(productRequest.getSupplierID());
        return product;
    }
}
