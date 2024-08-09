package com.hsenid.smartstock.dto.request;

import lombok.Data;

@Data
public class SupplierRequest {
    private String FirstName;
    private String LastName;
    private String contactPerson;
    private String email;
    private String phone;
    private String address;
}
