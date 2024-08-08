package com.hsenid.smartstock.dto.request;

import lombok.Data;

import java.time.LocalDate;

public record OrderRequest (
    int quantity,
    LocalDate orderDate,
    LocalDate expectedArrivalDate,
    String supplierId
){}
