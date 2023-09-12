package com.example.homework003.repository;

import com.example.homework003.model.entity.Category;
import com.example.homework003.model.request.CategoryRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryRepository {
    @Select("SELECT * FROM categories ORDER BY category_id ASC")
    @Result(property = "categoryId",column = "category_id")
    @Result(property = "categoryName",column = "category_name")
    List<Category> findAllCategory();

    @Select("SELECT * FROM categories WHERE category_id = #{categoryId}")
    @Result(property = "categoryId",column = "category_id")
    @Result(property = "categoryName",column = "category_name")
    Category getCategoryById(Integer categoryId);

    @Delete("DELETE FROM categories WHERE category_id = #{categoryId}")
    boolean deleteCategoryById(Integer categoryId);

    @Select("INSERT INTO categories (category_name) VALUES(#{request.categoryName}) "+
            "RETURNING category_id")
    Integer saveCategory(@Param("request") CategoryRequest categoryRequest);

    @Select("UPDATE categories " +
            "SET category_name = #{request.categoryName}" +
            " WHERE category_id = #{categoryId}"+
            " RETURNING category_id")
    Integer updateCategory(@Param("request") CategoryRequest categoryRequest, Integer categoryId);

    @Select("SELECT c.category_id,c.category_name FROM book_details bd "
            +"INNER JOIN categories c ON c.category_id = bd.category_id "+
            "WHERE bd.book_id = #{bookId}")
    @Result(property = "categoryId",column = "category_id")
    @Result(property = "categoryName",column = "category_name")
    List<Category> getCategoryByBookId(Integer bookId);


}
