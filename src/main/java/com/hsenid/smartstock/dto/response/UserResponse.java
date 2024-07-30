package com.hsenid.smartstock.dto.response;


import com.hsenid.smartstock.entity.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String password;
    private UserRole role;
}
