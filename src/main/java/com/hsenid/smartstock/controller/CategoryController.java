package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.common.ApiResponse;
import com.hsenid.smartstock.common.StatusCode;
import com.hsenid.smartstock.entity.Category;
import com.hsenid.smartstock.entity.SubCategory;
import com.hsenid.smartstock.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Endpoint to add a new main category
    @PostMapping
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        try {
            Category createdCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage(StatusCode.S0000.getMessage())
                            .withPayload(createdCategory)
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage(StatusCode.E5000.getMessage())
            );
        }
    }

    // Endpoint to add a subcategory to any level (category or subcategory)
    @PostMapping("/{parentId}/subcategories")
    public ResponseEntity<ApiResponse> addSubCategory(@PathVariable String parentId, @RequestBody SubCategory subCategory) {
        try {
            SubCategory createdSubCategory = categoryService.addSubCategory(parentId, subCategory);
            if (createdSubCategory != null) {
                return ResponseEntity.ok(
                        ApiResponse.forStatus(StatusCode.S0000)
                                .withMessage(StatusCode.S0000.getMessage())
                                .withPayload(createdSubCategory)
                );
            }
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E4004)
                            .withMessage(StatusCode.E4004.getMessage())
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage(StatusCode.E5000.getMessage())
            );
        }
    }

    // Endpoint to get a category by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable String id) {
        try {
            Optional<Category> categoryOpt = categoryService.getCategoryById(id);
            return categoryOpt.map(category ->
                    ResponseEntity.ok(
                            ApiResponse.forStatus(StatusCode.S0000)
                                    .withMessage(StatusCode.S0000.getMessage())
                                    .withPayload(category)
                    )
            ).orElseGet(() ->
                    ResponseEntity.ok(
                            ApiResponse.forStatus(StatusCode.E4004)
                                    .withMessage(StatusCode.E4004.getMessage())
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage(StatusCode.E5000.getMessage())
            );
        }
    }

    // Endpoint to get a subcategory by its ID
    @GetMapping("/subcategories/{id}")
    public ResponseEntity<ApiResponse> getSubCategoryById(@PathVariable String id) {
        try {
            Optional<SubCategory> subCategoryOpt = categoryService.getSubCategoryById(id);
            return subCategoryOpt.map(subCategory ->
                    ResponseEntity.ok(
                            ApiResponse.forStatus(StatusCode.S0000)
                                    .withMessage(StatusCode.S0000.getMessage())
                                    .withPayload(subCategory)
                    )
            ).orElseGet(() ->
                    ResponseEntity.ok(
                            ApiResponse.forStatus(StatusCode.E4004)
                                    .withMessage(StatusCode.E4004.getMessage())
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage(StatusCode.E5000.getMessage())
            );
        }
    }
}
