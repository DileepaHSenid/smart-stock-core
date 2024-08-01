package com.hsenid.smartstock.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String Status;
    private String ShippingId;
    private String description;
    private double price;
    private int stockQuantity;
    private String categoryId;
    private String supplierId;
}
