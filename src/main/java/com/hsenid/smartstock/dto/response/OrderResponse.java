package com.hsenid.smartstock.dto.response;

import java.time.LocalDate;

public record OrderResponse (
    int quantity,
    LocalDate orderDate,
    LocalDate expectedArrivalDate,
    String supplierId
){}
