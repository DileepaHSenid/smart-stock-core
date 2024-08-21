package com.hsenid.smartstock.dto.mapper;


import com.hsenid.smartstock.dto.request.SupplierRequest;
import com.hsenid.smartstock.dto.response.SupplierResponse;
import com.hsenid.smartstock.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {
    public SupplierResponse toSupplierResponse(Supplier supplier) {
        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setImg(supplier.getImg());
        response.setFirstName(supplier.getFirstName());
        response.setLastName(supplier.getLastName());
        response.setContactPerson(supplier.getContactPerson());
        response.setEmail(supplier.getEmail());
        response.setPhone(supplier.getPhone());
        response.setAddress(supplier.getAddress());
        return response;
    }
    public Supplier toSupplierRequest(SupplierRequest supplierRequest) {
        Supplier supplier = new Supplier();
        supplier.setImg(supplierRequest.getImg());
        supplier.setFirstName(supplierRequest.getFirstName());
        supplier.setLastName(supplierRequest.getLastName());
        supplier.setContactPerson(supplierRequest.getContactPerson());
        supplier.setEmail(supplierRequest.getEmail());
        supplier.setPhone(supplierRequest.getPhone());
        supplier.setAddress(supplierRequest.getAddress());

        return supplier;
    }
    public void updateSupplierFromRequest(SupplierRequest supplierRequest, Supplier supplier) {
        supplier.setImg(supplierRequest.getImg());
        supplier.setFirstName(supplierRequest.getFirstName());
        supplier.setLastName(supplierRequest.getLastName());
        supplier.setContactPerson(supplierRequest.getContactPerson());
        supplier.setEmail(supplierRequest.getEmail());
        supplier.setPhone(supplierRequest.getPhone());
        supplier.setAddress(supplierRequest.getAddress());
    }
}

