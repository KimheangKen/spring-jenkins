package com.example.homework003.controller;

import com.example.homework003.model.entity.Author;
import com.example.homework003.model.entity.Category;
import com.example.homework003.model.request.AuthorRequest;
import com.example.homework003.model.request.CategoryRequest;
import com.example.homework003.model.response.CustomResponse;
import com.example.homework003.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    @Operation(summary = "Get all category by id heheheheh")
    public ResponseEntity<CustomResponse<List<Category>>> getAllCategory(){
        CustomResponse<List<Category>> response = CustomResponse.<List<Category>>builder()
                .message("Get all category successfully")
                .payload(categoryService.getAllCategory())
                .httpStatus(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by Id")
    public ResponseEntity<CustomResponse<Category>> getCategoryById(@PathVariable("id") Integer categoryId){
        CustomResponse<Category> response;
        if(categoryService.getCategoryById(categoryId) != null){
            response = CustomResponse.<Category>builder()
                    .message("Get category by id successfully!")
                    .payload(categoryService.getCategoryById(categoryId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category by id hahahaha hehehehe")
    public ResponseEntity<CustomResponse<String>> deleteCategoryById(@PathVariable("id") Integer categoryId){
        CustomResponse<String> response;
        if(categoryService.deleteCategoryById(categoryId)){
            response = CustomResponse.<String>builder()
                    .message("Delete category successfully")
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    @Operation(summary = "Save new category")
    public ResponseEntity<CustomResponse<Category>> addNewCategory(@RequestBody CategoryRequest categoryRequest){
        Integer storeCategoryId = categoryService.addNewCategory(categoryRequest);
        if(storeCategoryId != null){
            CustomResponse<Category> response = CustomResponse.<Category>builder()
                    .message("Add new author successfully")
                    .payload(categoryService.getCategoryById(storeCategoryId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return null;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update category by Id")
    public ResponseEntity<CustomResponse<Category>> updateCategoryById(
            @RequestBody CategoryRequest categoryRequest, @PathVariable("id") Integer categoryId
    ){
        CustomResponse<Category> response;
        Integer idCategory = categoryService.updateCategory(categoryRequest,categoryId);
        if(idCategory != null){
            response = CustomResponse.<Category>builder()
                    .message("Updated author by Id Successfully!")
                    .payload(categoryService.getCategoryById(idCategory))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();

            return  ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
