package com.hsenid.smartstock.dto.request;

public record SupplierRequest (
        String FirstName,
        String LastName,
        String contactPerson,
        String email,
        String phone,
        String address
){}
