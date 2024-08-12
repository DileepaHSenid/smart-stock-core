package com.hsenid.smartstock.service;


import com.hsenid.smartstock.dto.mapper.SupplierMapper;
import com.hsenid.smartstock.dto.request.SupplierRequest;
import com.hsenid.smartstock.dto.response.SupplierResponse;
import com.hsenid.smartstock.entity.Supplier;
import com.hsenid.smartstock.repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private SupplierMapper supplierMapper;

    public List<Supplier> getAllSuppliers() {

        return supplierRepo.findAll();
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public void deleteSupplier(String id) {
        supplierRepo.deleteById(id);
    }

    public Optional<SupplierResponse> updateSupplier(String id, SupplierRequest supplierRequest) {
        Optional<Supplier> supplierOptional = supplierRepo.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier supplier = supplierOptional.get();
            supplier.setFirstName(supplierRequest.FirstName());
            supplier.setLastName(supplierRequest.LastName());
            supplier.setContactPerson(supplierRequest.contactPerson());
            supplier.setEmail(supplierRequest.email());
            supplier.setPhone(supplierRequest.phone());
            Supplier updatedSupplier = supplierRepo.save(supplier);
            return Optional.of(supplierMapper.toSupplierResponse(updatedSupplier));
        }
        return Optional.empty();
    }

}
