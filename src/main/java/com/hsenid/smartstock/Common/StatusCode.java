package com.hsenid.smartstock.Common;

import lombok.Getter;

@Getter
public enum StatusCode {

    // Success codes
    S0000("S0000", "Success", "Operation completed successfully"),
    S0001("S0001", "Created", "Resource created successfully"),
    S0002("S0002", "Updated", "Resource updated successfully"),
    S0003("S0003", "Deleted", "Resource deleted successfully"),

    // Error codes
    E4000("E4000", "Bad Request", "The request was invalid or cannot be served"),
    E4001("E4001", "Unauthorized", "Authentication credentials were missing or incorrect"),
    E4003("E4003", "Forbidden", "The server understood the request but refuses to authorize it"),
    E4004("E4004", "Not Found", "The requested resource could not be found"),
    E4009("E4009", "Conflict", "The request could not be completed due to a conflict with the current state of the resource"),
    E4022("E4022", "Unprocessable Entity", "The request was well-formed but was unable to be followed due to semantic errors"),

    E5000("E5000", "Internal Server Error", "The server encountered an unexpected condition which prevented it from fulfilling the request"),
    E5001("E5001", "Not Implemented", "The server does not support the functionality required to fulfill the request"),
    E5002("E5002", "Service Unavailable", "The server is currently unable to handle the request due to temporary overloading or maintenance of the server");

    private final String code;
    private final String title;
    private final String message;

    StatusCode(String code, String title, String message) {
        this.code = code;
        this.title = title;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.name().startsWith("S");
    }

    public static StatusCode fromCode(String code) {
        for (StatusCode status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }
}