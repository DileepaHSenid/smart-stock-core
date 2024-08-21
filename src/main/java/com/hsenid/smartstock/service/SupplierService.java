package com.hsenid.smartstock.service;



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

    public List<Supplier> getAllSuppliers() {

        return supplierRepo.findAll();
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public boolean deleteSupplier(String supplierId) {
        if (supplierRepo.existsById(supplierId)) {
            supplierRepo.deleteById(supplierId);
            return true;
        }
        return false;
    }
    public Optional<Supplier> getSupplierById(String id) {
        return supplierRepo.findById(id);
    }
    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }
}
