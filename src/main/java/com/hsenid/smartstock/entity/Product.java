package com.hsenid.smartstock.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;
    private String img;
    private String name;
    private String Status;
    private String ShippingId;
    private String description;
    private double price;
    private int QuantityInStock;
    private int QuantityToReceive;
    private Date LastOrderedDate;
    private String categoryId;
    private String supplierFirstName;
    private String supplierLastName;
    private String supplierID;

}
