package com.example.homework003.repository;

import com.example.homework003.model.entity.Author;
import com.example.homework003.model.request.AuthorRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AuthorRepository {
    @Select("SELECT * FROM authors ORDER BY author_id")
    @Results(
            id = "authorMapper",
            value ={
                    @Result(property = "authorId", column = "author_id"),
                    @Result(property = "authorName", column = "author_name")
            }
    )
    List<Author> findAllAuthor();

    @Select("SELECT * FROM authors WHERE author_id=#{authorId}")
    @ResultMap("authorMapper")
    Author getAuthorById(Integer authorId);


    @Delete("DELETE FROM authors WHERE author_id = #{authorId}")
    boolean deleteAuthorById(Integer authorId);

    @Select("INSERT INTO authors (author_name, gender) VALUES(#{request.authorName},#{request.gender}) "+
            "RETURNING author_id")
    Integer saveAuthor(@Param("request") AuthorRequest authorRequest);

    @Select("UPDATE authors " +
            "SET author_name = #{request.authorName}," +
            " gender = #{request.gender}" +
            " WHERE author_id = #{authorId}"+
            " RETURNING author_id")

    Integer updateAuthor(@Param("request") AuthorRequest authorRequest, Integer authorId);



}
