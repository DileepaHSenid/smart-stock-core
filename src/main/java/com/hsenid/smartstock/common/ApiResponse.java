package com.hsenid.smartstock.common;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class ApiResponse {
    private StatusCode status;
    private String message;
    private LocalDateTime timestamp;
    private Object payload;

    private ApiResponse() {
        timestamp = LocalDateTime.now();
    }

    public static ApiResponse forStatus(@NonNull StatusCode status) {
        ApiResponse response = new ApiResponse();
        response.status = status;
        return response;
    }

    public ApiResponse withMessage(String message, String... params) {
        this.message = String.format(message, params);
        return this;
    }

    public ApiResponse withPayload(Object payload) {
        this.payload = payload;
        return this;
    }
}