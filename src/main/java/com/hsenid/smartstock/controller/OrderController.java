package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.dto.request.OrderRequest;
import com.hsenid.smartstock.dto.response.OrderResponse;
import com.hsenid.smartstock.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        Optional<OrderResponse> createdOrder = orderService.createOrder(orderRequest);
        return createdOrder
                .map(order -> new ResponseEntity<>(order, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable String id, @RequestBody OrderRequest orderRequest) {
        Optional<OrderResponse> updatedOrder = orderService.updateOrder(id, orderRequest);
        return updatedOrder
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        if (orderService.getOrderById(id).isPresent()) {
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable String id) {
        Optional<OrderResponse> order = orderService.getOrderById(id);
        return order
                .map(o -> new ResponseEntity<>(o, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @GetMapping("/supplier/{supplierId}")
//    public ResponseEntity<List<OrderResponse>> getOrdersBySupplierId(@PathVariable String supplierId) {
//        List<OrderResponse> orders = orderService.getOrdersBySupplierId(supplierId);
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }
}




//
//package com.hsenid.smartstock.controller;
//
//import com.hsenid.smartstock.common.ApiResponse;
//import com.hsenid.smartstock.common.StatusCode;
//import com.hsenid.smartstock.dto.request.OrderRequest;
//import com.hsenid.smartstock.dto.response.OrderResponse;
//import com.hsenid.smartstock.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @PostMapping
//    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderRequest orderRequest) {
//        try {
//            Optional<OrderResponse> createdOrder = orderService.createOrder(orderRequest);
//            return createdOrder
//                    .map(order -> ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
//                            .withMessage(StatusCode.S0000.getMessage())
//                            .withPayload(order)))
//                    .orElseGet(() -> ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
//                            .withMessage("Order creation failed")));
//        } catch (Exception e) {
//            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
//                    .withMessage(e.getMessage()));
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse> updateOrder(@PathVariable String id, @RequestBody OrderRequest orderRequest) {
//        try {
//            Optional<OrderResponse> updatedOrder = orderService.updateOrder(id, orderRequest);
//            return updatedOrder
//                    .map(order -> ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
//                            .withMessage(StatusCode.S0000.getMessage())
//                            .withPayload(order)))
//                    .orElseGet(() -> ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E4004)
//                            .withMessage(StatusCode.E4004.getMessage())));
//        } catch (Exception e) {
//            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
//                    .withMessage(e.getMessage()));
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable String id) {
//        try {
//            Optional<OrderResponse> order = orderService.getOrderById(id);
//            if (order.isPresent()) {
//                orderService.deleteOrder(id);
//                return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
//                        .withMessage("Order deleted successfully"));
//            } else {
//                return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E4004)
//                        .withMessage(StatusCode.E4004.getMessage()));
//            }
//        } catch (Exception e) {
//            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
//                    .withMessage(e.getMessage()));
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<ApiResponse> getAllOrders() {
//        try {
//            List<OrderResponse> orders = orderService.getAllOrders();
//            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
//                    .withMessage(StatusCode.S0000.getMessage())
//                    .withPayload(orders));
//        } catch (Exception e) {
//            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
//                    .withMessage(e.getMessage()));
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse> getOrderById(@PathVariable String id) {
//        try {
//            Optional<OrderResponse> order = orderService.getOrderById(id);
//            return order
//                    .map(o -> ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
//                            .withMessage(StatusCode.S0000.getMessage())
//                            .withPayload(o)))
//                    .orElseGet(() -> ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E4004)
//                            .withMessage(StatusCode.E4004.getMessage())));
//        } catch (Exception e) {
//            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
//                    .withMessage(e.getMessage()));
//        }
//    }
//
//    @GetMapping("/supplier/{supplierId}")
//    public ResponseEntity<ApiResponse> getOrdersBySupplierId(@PathVariable String supplierId) {
//        try {
//            List<OrderResponse> orders = orderService.getOrdersBySupplierId(supplierId);
//            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
//                    .withMessage(StatusCode.S0000.getMessage())
//                    .withPayload(orders));
//        } catch (Exception e) {
//            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
//                    .withMessage(e.getMessage()));
//        }
//    }
//}
