package com.hsenid.smartstock.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "suppliers")
public class Supplier {
    @Id
    private String id; //Generating Auto
    private String img;
    private String firstName;
    private String lastName;
    private String contactPerson;
    private String email;
    private String phone;
    private String address;

}
