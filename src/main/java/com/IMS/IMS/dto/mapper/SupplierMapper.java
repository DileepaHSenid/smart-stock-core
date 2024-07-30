package com.IMS.IMS.dto.mapper;

import com.IMS.IMS.dto.request.SupplierRequest;
import com.IMS.IMS.dto.response.SupplierResponse;
import com.IMS.IMS.model.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {
    public SupplierResponse toSupplierResponse(Supplier supplier) {
        SupplierResponse response = new SupplierResponse();
        response.setSupplierFirstName(supplier.getFirstName());
        response.setSupplierLastName(supplier.getLastName());
        response.setContactPerson(supplier.getContactPerson());
        response.setEmail(supplier.getEmail());
        response.setPhone(supplier.getPhone());
        response.setAddress(supplier.getAddress());
        return response;
    }
    public Supplier toSupplierRequest(SupplierRequest supplierRequest) {
        Supplier supplier = new Supplier();
        supplier.setFirstName(supplierRequest.getSupplierFirstName());
        supplier.setLastName(supplierRequest.getSupplierLastName());
        supplier.setContactPerson(supplierRequest.getContactPerson());
        supplier.setEmail(supplierRequest.getEmail());
        supplier.setPhone(supplierRequest.getPhone());
        supplier.setAddress(supplierRequest.getAddress());

        return supplier;
    }

}

