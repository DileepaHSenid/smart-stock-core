package com.IMS.IMS.service;

import com.IMS.IMS.model.Supplier;
import com.IMS.IMS.repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SupplierService {
    @Autowired
    private SupplierRepo supplierRepo;

    public List<Supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }
}
