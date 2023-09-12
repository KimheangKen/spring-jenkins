package com.example.homework003.service;

import com.example.homework003.model.entity.Category;
import com.example.homework003.model.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    Category getCategoryById (Integer categoryId);
    boolean deleteCategoryById (Integer categoryId);
    Integer addNewCategory (CategoryRequest categoryRequest);

    Integer updateCategory (CategoryRequest categoryRequest, Integer categoryId);

}
