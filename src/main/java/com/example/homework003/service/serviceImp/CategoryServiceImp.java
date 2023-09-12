package com.example.homework003.service.serviceImp;

import com.example.homework003.exception.CheckException;
import com.example.homework003.exception.IdNotFoundException;
import com.example.homework003.model.entity.Author;
import com.example.homework003.model.entity.Category;
import com.example.homework003.model.request.CategoryRequest;
import com.example.homework003.repository.CategoryRepository;
import com.example.homework003.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAllCategory();
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        Category category = categoryRepository.getCategoryById(categoryId);
        if(category == null){
            throw new IdNotFoundException("category",categoryId);
        }else return category;
    }

    @Override
    public boolean deleteCategoryById(Integer categoryId) {
        boolean isDeleteCategoryById = categoryRepository.deleteCategoryById(categoryId);
        if(isDeleteCategoryById){
            return isDeleteCategoryById;
        }else throw new IdNotFoundException("category",categoryId);

    }

    @Override
    public Integer addNewCategory(CategoryRequest categoryRequest) {
        if((categoryRequest.getCategoryName().trim().isEmpty())){
            throw new CheckException("field can not empty");
        }else {
            Integer getCategoryId = categoryRepository.saveCategory(categoryRequest);
            return getCategoryId;
        }
    }

    @Override
    public Integer updateCategory(CategoryRequest categoryRequest, Integer categoryId) {
        Category getCategoryById = categoryRepository.getCategoryById(categoryId);
        if(getCategoryById == null){
            throw new IdNotFoundException("category",categoryId);
        }else if(categoryRequest.getCategoryName().trim().isEmpty()){
            throw new CheckException("field can not empty");
        }else {
            Integer updateCategoryId = categoryRepository.updateCategory(categoryRequest, categoryId);
            return updateCategoryId;
        }
    }
}
