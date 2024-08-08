package com.hsenid.smartstock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String status;
    private String shippingId;
    private String description;
    private double price;
    private int stockQuantity;

    @DBRef  // Use @DBRef to reference the Category entity
    private String categoryId;

    private String supplierId;
}
