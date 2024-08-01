package com.hsenid.smartstock.dto.mapper;

import com.hsenid.smartstock.entity.Category;
import com.hsenid.smartstock.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Product toProductWithCategory(Product product, Category category) {
        product.setCategoryId(category.getId());
        return product;
    }
}
