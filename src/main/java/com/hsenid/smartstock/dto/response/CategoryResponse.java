package com.hsenid.smartstock.dto.response;

import lombok.Data;

import java.util.Map;
@Data
public class CategoryResponse {
    private String id;
    private String name;
    private Map<String, String> subCategories;
}
