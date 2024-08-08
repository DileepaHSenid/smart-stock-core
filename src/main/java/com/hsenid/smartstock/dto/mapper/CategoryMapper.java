package com.hsenid.smartstock.dto.mapper;

import com.hsenid.smartstock.dto.request.CategoryRequest;
import com.hsenid.smartstock.dto.response.CategoryResponse;
import com.hsenid.smartstock.entity.Category;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CategoryMapper {

    public CategoryResponse toCategoryResponse(Category category) {
        CategoryResponse response = new CategoryResponse();

        response.setId(category.getId());
        response.setName(category.getName());

        // Convert subCategories from Map<String, Category> to Map<String, String> (ID to name)
        Map<String, String> subCategories = new HashMap<>();
        for (Map.Entry<String, Category> entry : category.getSubCategories().entrySet()) {
            subCategories.put(entry.getKey(), entry.getValue().getName()); // Assuming subCategory has a getName() method
        }
        response.setSubCategories(subCategories);

        return response;
    }

    public Category toCategoryRequest(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setId(categoryRequest.getId());
        category.setName(categoryRequest.getName());

        // Convert subCategories from Map<String, String> (ID to name) to Map<String, Category>
        Map<String, Category> subCategories = new HashMap<>();
        for (Map.Entry<String, String> entry : categoryRequest.getSubCategories().entrySet()) {
            Category subCategory = new Category();
            subCategory.setId(entry.getKey());
            subCategory.setName(entry.getValue());
            subCategories.put(entry.getKey(), subCategory);
        }
        category.setSubCategories(subCategories);

        return category;
    }
}
