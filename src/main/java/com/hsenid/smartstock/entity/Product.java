package com.hsenid.smartstock.entity;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String name;
    private String Status;
    private String ShippingId;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
    private String supplierId;
}
