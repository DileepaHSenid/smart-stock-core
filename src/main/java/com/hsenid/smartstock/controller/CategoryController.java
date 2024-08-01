package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.common.ApiResponse;
import com.hsenid.smartstock.common.StatusCode;
import com.hsenid.smartstock.entity.Category;
import com.hsenid.smartstock.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

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

    @PostMapping("/{parentId}/subcategories")
    public ResponseEntity<ApiResponse> addSubCategory(@PathVariable String parentId, @RequestBody Category subCategory) {
        try {
            Category createdSubCategory = categoryService.addSubCategory(parentId, subCategory);
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
}
