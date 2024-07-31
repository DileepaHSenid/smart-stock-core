package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hsenid.smartstock.service.CategoryService;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    @PostMapping("/{parentId}/subcategories")
    public ResponseEntity<Category> addSubCategory(@PathVariable String parentId, @RequestBody Category subCategory) {
        Category createdSubCategory = categoryService.addSubCategory(parentId, subCategory);
        if (createdSubCategory != null) {
            return ResponseEntity.ok(createdSubCategory);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String id) {
        Optional<Category> categoryOpt = categoryService.getCategoryById(id);
        return categoryOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}



