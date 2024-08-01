package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.dto.mapper.SupplierMapper;
import com.hsenid.smartstock.dto.request.SupplierRequest;
import com.hsenid.smartstock.dto.response.SupplierResponse;
import com.hsenid.smartstock.entity.Supplier;
import com.hsenid.smartstock.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/suppliers")
public class SupplierController {
   @Autowired
   private SupplierService supplierService;
   @Autowired
   private SupplierMapper supplierMapper;


   @GetMapping
   public List<SupplierResponse> getAllSuppliers() {
      return supplierService.getAllSuppliers()
              .stream()
              .map(supplierMapper::toSupplierResponse)
              .collect(Collectors.toList());
   }


   @PostMapping("/CreateSupplier")
   public ResponseEntity<SupplierResponse> createSupplier(@RequestBody SupplierRequest supplierRequest) {
      Supplier supplier = supplierMapper.toSupplierRequest(supplierRequest);
      Supplier createdSupplier = supplierService.createSupplier(supplier);
      SupplierResponse supplierResponse = supplierMapper.toSupplierResponse(createdSupplier);
      return new ResponseEntity<>(supplierResponse, HttpStatus.CREATED);
   }

}
