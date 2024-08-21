package com.hsenid.smartstock.dto.request;


import com.hsenid.smartstock.enums.UserRoles;
import lombok.Data;

@Data
public class UserRequest {
    private String UserId;
    private String img;
    private String username;
    private String password;
    private UserRoles role;
}
