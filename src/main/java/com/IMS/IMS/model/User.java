package com.IMS.IMS.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    private String id; //Generating Auto
    private String username;
    private String password;
    private UserRole role;
}
