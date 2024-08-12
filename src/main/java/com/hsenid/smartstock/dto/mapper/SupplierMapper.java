package com.hsenid.smartstock.dto.mapper;

import com.hsenid.smartstock.dto.request.SupplierRequest;
import com.hsenid.smartstock.dto.response.SupplierResponse;
import com.hsenid.smartstock.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {
    public SupplierResponse toSupplierResponse(Supplier supplier) {
        return new SupplierResponse(
                supplier.getId(),
                supplier.getFirstName(),
                supplier.getLastName(),
                supplier.getContactPerson(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getAddress()
        );
    }

    public Supplier toSupplierRequest(SupplierRequest supplierRequest) {
        Supplier supplier = new Supplier();
        supplier.setFirstName(supplierRequest.FirstName());
        supplier.setLastName(supplierRequest.LastName());
        supplier.setContactPerson(supplierRequest.contactPerson());
        supplier.setEmail(supplierRequest.email());
        supplier.setPhone(supplierRequest.phone());
        supplier.setAddress(supplierRequest.address());
        return supplier;
    }
}

