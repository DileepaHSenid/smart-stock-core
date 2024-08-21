package com.hsenid.smartstock.dto.response;


import com.hsenid.smartstock.enums.UserRoles;
import lombok.Data;

@Data
public class UserResponse {
    private String userId;
    private String img;
    private String username;
    private UserRoles role;
}
