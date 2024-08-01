package com.hsenid.smartstock.dto.response;

import lombok.Data;

public record StockResponse (
        String id,
        String productId,
        int quantity,
        String name,
        boolean isOrder
) {}