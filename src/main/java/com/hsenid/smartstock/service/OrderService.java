package com.hsenid.smartstock.service;

import com.hsenid.smartstock.dto.mapper.OrderMapper;
import com.hsenid.smartstock.dto.request.OrderRequest;
import com.hsenid.smartstock.dto.response.OrderResponse;
import com.hsenid.smartstock.entity.Order;
import com.hsenid.smartstock.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    public Optional<OrderResponse> createOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toOrderRequest(orderRequest);
        Order savedOrder = orderRepository.save(order);
        return Optional.of(orderMapper.toOrderResponse(savedOrder));
    }

    public Optional<OrderResponse> updateOrder(String id, OrderRequest orderRequest) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setQuantity(orderRequest.quantity());
            order.setOrderDate(orderRequest.orderDate());
            order.setExpectedArrivalDate(orderRequest.expectedArrivalDate());
            order.setSupplierId(orderRequest.supplierId());
            Order updatedOrder = orderRepository.save(order);
            return Optional.of(orderMapper.toOrderResponse(updatedOrder));
        }
        return Optional.empty();
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    public Optional<OrderResponse> getOrderById(String id) {
        return orderRepository.findById(id)
                .map(orderMapper::toOrderResponse);
    }

    public List<OrderResponse> getOrdersBySupplierId(String supplierId) {
        return orderRepository.findBySupplierId(supplierId).stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }
}
