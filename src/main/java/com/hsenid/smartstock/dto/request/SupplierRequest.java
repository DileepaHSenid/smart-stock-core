package com.hsenid.smartstock.dto.request;

import lombok.Data;

@Data
public class SupplierRequest {
    private String img;
    private String firstName;
    private String lastName;
    private String contactPerson;
    private String email;
    private String phone;
    private String address;

}
