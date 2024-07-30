package com.IMS.IMS.dto.request;

import com.IMS.IMS.model.UserRole;
import lombok.Data;

@Data
public class UserRequest {
    private String id; //Generating Auto
    private String username;
    private String password;
    private UserRole role;
}
