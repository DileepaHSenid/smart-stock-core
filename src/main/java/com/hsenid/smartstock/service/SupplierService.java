package com.hsenid.smartstock.service;


import com.hsenid.smartstock.entity.Supplier;
import com.hsenid.smartstock.repository.SupplierRepo;
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

    public void deleteSupplier(String id) {
        supplierRepo.deleteById(id);
    }

}
