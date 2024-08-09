package com.hsenid.smartstock.dto.request;

public record StockRequest (
        String productId,
        int quantity,
        String name,
        boolean isOrder
) {}