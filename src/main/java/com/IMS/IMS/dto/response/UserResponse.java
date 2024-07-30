package com.IMS.IMS.dto.response;

import com.IMS.IMS.model.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String password;
    private UserRole role;
}
