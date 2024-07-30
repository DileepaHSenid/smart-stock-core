package com.hsenid.smartstock.entity;

import com.hsenid.smartstock.enums.Status;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "product")
public class Product {

    @Id
    private String modelNo;
    private String name;
    private int quantity;
    private String description;
    private double price;
    private String category;
    private String supplierId;
    private Status status;
}
