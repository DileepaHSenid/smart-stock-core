package com.hsenid.smartstock.service;

import com.hsenid.smartstock.entity.Category;
import com.hsenid.smartstock.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    public Category addSubCategory(String parentId, Category subCategory) {
        Optional<Category> parentCategoryOpt = categoryRepo.findById(parentId);
        if (parentCategoryOpt.isPresent()) {
            Category parentCategory = parentCategoryOpt.get();
            parentCategory.addSubCategory(subCategory);
            categoryRepo.save(parentCategory);
            return subCategory;
        }
        return null;
    }

    public Optional<Category> getCategoryById(String id) {
        return categoryRepo.findById(id);
    }
}
