package com.hsenid.smartstock.dto.request;

import lombok.Data;

@Data
public class SupplierRequest {
    private String supplierFirstName;
    private String supplierLastName;
    private String contactPerson;
    private String email;
    private String phone;
    private String address;
}
