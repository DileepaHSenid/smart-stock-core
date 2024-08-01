package com.hsenid.smartstock.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Stock")
public class Stock {

    @Id
    private String id;
    private String productId;
    private int quantity;
    private String name;
    private boolean is_Order;

}
