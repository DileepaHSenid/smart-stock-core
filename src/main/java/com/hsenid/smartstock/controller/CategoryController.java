package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.common.ApiResponse;
import com.hsenid.smartstock.common.StatusCode;
import com.hsenid.smartstock.entity.Category;
import com.hsenid.smartstock.entity.SubCategory;
import com.hsenid.smartstock.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        try {
            Category createdCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage(StatusCode.S0000.getMessage())
                            .withPayload(createdCategory)
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    (ApiResponse.forStatus(StatusCode.E4001))
                            .withMessage("Category already exists")
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


    // Endpoint to get all categories
    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage(StatusCode.S0000.getMessage())
                            .withPayload(categories)
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage(StatusCode.E5000.getMessage())
            );
        }
    }
}



