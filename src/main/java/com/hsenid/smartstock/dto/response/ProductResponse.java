package com.hsenid.smartstock.dto.response;

import lombok.Data;

@Data
public class ProductResponse {
    private String name;
    private String Status;
    private String ShippingId;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
    private String supplierId;
}
