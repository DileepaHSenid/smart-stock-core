package com.IMS.IMS.dto.response;

import lombok.Data;

@Data
public class SupplierResponse {
    private String supplierFirstName;
    private String supplierLastName;
    private String contactPerson;
    private String email;
    private String phone;
    private String address;
}
