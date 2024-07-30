package com.hsenid.smartstock.dto.request;


import com.hsenid.smartstock.entity.UserRole;
import lombok.Data;

@Data
public class UserRequest {
    private String id; //Generating Auto
    private String username;
    private String password;
    private UserRole role;
}
