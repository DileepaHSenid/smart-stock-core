package com.hsenid.smartstock.dto;

import com.hsenid.smartstock.Common.StatusCode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiResponse<T> {
    private String code;
    private String status;
    private String message;
    private T payload;

    public static <T> ApiResponse<T> forStatus(StatusCode statusCode) {
        return new ApiResponse<T>()
                .setCode(statusCode.getCode())
                .setStatus(statusCode.getTitle())
                .setMessage(statusCode.getMessage());
    }

    public ApiResponse<T> withPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public ApiResponse<T> withMessage(String message) {
        this.message = message;
        return this;
    }
}