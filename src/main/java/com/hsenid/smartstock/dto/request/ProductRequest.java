package com.hsenid.smartstock.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String Status;
    private String ShippingId;
    private String description;
    private double price;
    private int stockQuantity;
    private String categoryId;
    private String supplierId;
}
