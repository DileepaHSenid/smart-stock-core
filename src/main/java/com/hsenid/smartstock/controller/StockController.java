package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.common.ApiResponse;
import com.hsenid.smartstock.common.StatusCode;
import com.hsenid.smartstock.dto.request.StockRequest;
import com.hsenid.smartstock.dto.response.StockResponse;
import com.hsenid.smartstock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<ApiResponse> createStock(@RequestBody StockRequest stockRequest) {
        try {
            Optional<StockResponse> createdStock = stockService.createStock(stockRequest);
            return createdStock
                    .map(stock -> ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage(StatusCode.S0000.getMessage())
                            .withPayload(stock)))
                    .orElseGet(() -> ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage("Stock creation failed")));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
                    .withMessage(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateStock(@PathVariable String id, @RequestBody StockRequest stockRequest) {
        try {
            Optional<StockResponse> updatedStock = stockService.updateStock(id, stockRequest);
            return updatedStock
                    .map(stock -> ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage(StatusCode.S0000.getMessage())
                            .withPayload(stock)))
                    .orElseGet(() -> ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E4004)
                            .withMessage(StatusCode.E4004.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
                    .withMessage(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStock(@PathVariable String id) {
        try {
            stockService.deleteStock(id);
            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
                    .withMessage("Stock deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
                    .withMessage(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllStocks() {
        try {
            List<StockResponse> stocks = stockService.getAllStocks();
            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
                    .withMessage(StatusCode.S0000.getMessage())
                    .withPayload(stocks));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
                    .withMessage(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getStockById(@PathVariable String id) {
        try {
            Optional<StockResponse> stock = stockService.getStockById(id);
            return stock
                    .map(s -> ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage(StatusCode.S0000.getMessage())
                            .withPayload(s)))
                    .orElseGet(() -> ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E4004)
                            .withMessage(StatusCode.E4004.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
                    .withMessage(e.getMessage()));
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> getStocksByProductId(@PathVariable String productId) {
        try {
            Optional<StockResponse> stocks = stockService.getStockById(productId);
            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
                    .withMessage(StatusCode.S0000.getMessage())
                    .withPayload(stocks));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
                    .withMessage(e.getMessage()));
        }
    }
}
