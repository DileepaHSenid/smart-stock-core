package com.IMS.IMS.controller;
import com.IMS.IMS.dto.mapper.SupplierMapper;
import com.IMS.IMS.dto.request.SupplierRequest;
import com.IMS.IMS.dto.response.SupplierResponse;
import com.IMS.IMS.model.Supplier;
import com.IMS.IMS.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping
public class SupplierController {
   @Autowired
   private SupplierService supplierService;
   @Autowired
   private SupplierMapper supplierMapper;


   @GetMapping("/suppliers")
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
