package com.hsenid.smartstock.service;

import com.hsenid.smartstock.entity.Category;
import com.hsenid.smartstock.entity.SubCategory;
import com.hsenid.smartstock.repository.CategoryRepo;
import com.hsenid.smartstock.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private SubCategoryRepository subCategoryRepo;

    // Add a new main category
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    // Add a subcategory to any level
    public SubCategory addSubCategory(String parentId, SubCategory subCategory) {
        // Try to find the parent in the Category repository
        Optional<Category> parentCategoryOpt = categoryRepo.findById(parentId);
        if (parentCategoryOpt.isPresent()) {
            Category parentCategory = parentCategoryOpt.get();
            subCategoryRepo.save(subCategory); // Save the subcategory to the database
            parentCategory.getSubCategories().add(subCategory); // Link the subcategory to the parent
            categoryRepo.save(parentCategory); // Save the parent category with the new subcategory
            return subCategory;
        }

        // If not found in Category, try to find it in the SubCategory repository
        Optional<SubCategory> parentSubCategoryOpt = subCategoryRepo.findById(parentId);
        if (parentSubCategoryOpt.isPresent()) {
            SubCategory parentSubCategory = parentSubCategoryOpt.get();
            subCategoryRepo.save(subCategory); // Save the nested subcategory to the database
            parentSubCategory.getSubCategories().add(subCategory); // Link the nested subcategory to the parent subcategory
            subCategoryRepo.save(parentSubCategory); // Save the parent subcategory with the new nested subcategory
            return subCategory;
        }

        // If no parent found, return null or throw an exception
        return null;
    }

    // Fetch a category by its ID
    public Optional<Category> getCategoryById(String id) {
        return categoryRepo.findById(id);
    }

    // Fetch a subcategory by its ID
    public Optional<SubCategory> getSubCategoryById(String id) {
        return subCategoryRepo.findById(id);
    }

    // Fetch all categories
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
