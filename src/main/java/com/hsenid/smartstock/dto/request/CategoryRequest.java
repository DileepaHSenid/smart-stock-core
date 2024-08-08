package com.hsenid.smartstock.dto.request;

import lombok.Data;

import java.util.Map;
@Data
public class CategoryRequest {
    private String id;
    private String name;
    private Map<String, String> subCategories;
}
