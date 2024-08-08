package com.hsenid.smartstock.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "order")
public class Order {

    private String productId;
    private int quantity;
    private LocalDate orderDate;
    private LocalDate expectedArrivalDate;
    private String supplierId;

}
