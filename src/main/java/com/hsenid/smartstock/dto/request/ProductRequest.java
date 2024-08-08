package com.hsenid.smartstock.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String status;
    private String shippingId;
    private String description;
    private double price;
    private int stockQuantity;
    private String supplierId;


}
