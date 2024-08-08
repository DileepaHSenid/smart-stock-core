package com.hsenid.smartstock.dto.response;

import lombok.Data;

@Data
public class SupplierResponse {
    private String id;
    private String FirstName;
    private String LastName;
    private String contactPerson;
    private String email;
    private String phone;
    private String address;
}
