package com.hsenid.smartstock.dto.response;

public record SupplierResponse (
        String id,
        String FirstName,
        String LastName,
        String contactPerson,
        String email,
        String phone,
        String address
){}
