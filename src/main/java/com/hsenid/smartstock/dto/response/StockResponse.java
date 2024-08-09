package com.hsenid.smartstock.dto.response;

public record StockResponse (
        String id,
        String productId,
        int quantity,
        String name,
        boolean isOrder
) {}