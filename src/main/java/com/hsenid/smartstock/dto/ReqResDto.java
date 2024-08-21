package com.hsenid.smartstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsenid.smartstock.entity.User;
import com.hsenid.smartstock.enums.UserRoles;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqResDto {

    private int statusCode;
    private String error;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String message;
    private String firstName;
    private String lastName;
    private String username;
    private UserRoles role;
    private String password;
    private User user;
    
}
