package com.hsenid.smartstock.dto.mapper;

import com.hsenid.smartstock.dto.request.OrderRequest;
import com.hsenid.smartstock.dto.response.OrderResponse;
import com.hsenid.smartstock.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toOrderResponse(Order order){
        return new OrderResponse(
            order.getQuantity(),
            order.getOrderDate(),
            order.getExpectedArrivalDate(),
            order.getSupplierId()
        );
    }

    public Order toOrderRequest(OrderRequest orderRequest){
        Order order = new Order();
        order.setQuantity(orderRequest.quantity());
        order.setOrderDate(orderRequest.orderDate());
        order.setExpectedArrivalDate(orderRequest.expectedArrivalDate());
        order.setSupplierId(orderRequest.supplierId());
        return order;
    }
}
