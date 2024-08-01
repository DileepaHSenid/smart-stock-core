package com.hsenid.smartstock.dto.request;

import lombok.Data;

public record StockRequest (
        String productId,
        int quantity,
        String name,
        boolean isOrder
) {}