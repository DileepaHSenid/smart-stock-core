package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.common.ApiResponse;
import com.hsenid.smartstock.common.StatusCode;
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
@CrossOrigin(origins = "http://localhost:4200")
public class SupplierController {
   @Autowired
   private SupplierService supplierService;

   @Autowired
   private SupplierMapper supplierMapper;

   @GetMapping
   public ResponseEntity<ApiResponse> getAllSuppliers() {
      try {
         List<SupplierResponse> suppliers = supplierService.getAllSuppliers()
                 .stream()
                 .map(supplierMapper::toSupplierResponse)
                 .collect(Collectors.toList());

         return ResponseEntity.ok(
                 ApiResponse.forStatus(StatusCode.S0000)
                         .withMessage(StatusCode.S0000.getMessage())
                         .withPayload(suppliers)
         );
      } catch (Exception e) {
         return ResponseEntity.ok(
                 ApiResponse.forStatus(StatusCode.E5000)
                         .withMessage(StatusCode.E5000.getMessage())
         );
      }
   }

   @PostMapping("/createsupplier")
   public ResponseEntity<ApiResponse> createSupplier(@RequestBody SupplierRequest supplierRequest) {
      try {
         Supplier supplier = supplierMapper.toSupplierRequest(supplierRequest);
         Supplier createdSupplier = supplierService.createSupplier(supplier);
         SupplierResponse supplierResponse = supplierMapper.toSupplierResponse(createdSupplier);

         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(ApiResponse.forStatus(StatusCode.S0000)
                         .withMessage(StatusCode.S0000.getMessage())
                         .withPayload(supplierResponse));
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body(ApiResponse.forStatus(StatusCode.E5000)
                         .withMessage(StatusCode.E5000.getMessage()));
      }
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<ApiResponse> deleteSupplier(@PathVariable String id) {
      try {
         supplierService.deleteSupplier(id);
         return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
                 .withMessage("Stock deleted successfully"));
      } catch (Exception e) {
         return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
                 .withMessage(e.getMessage()));
      }
   }
}
