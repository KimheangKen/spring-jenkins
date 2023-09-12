package com.example.homework003.repository;

import com.example.homework003.model.entity.Book;
import com.example.homework003.model.request.BookRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookRepository {
    @Select("SELECT * FROM books ORDER BY book_id ASC")
    @Results(
            id = "bookMapper",
            value = {
                    @Result(property = "bookId", column = "book_id"),
                    @Result(property = "publishedDate", column = "published_date"),
                    @Result(property = "title", column = "title"),
                    @Result(property = "author" , column = "author_id",
                            one = @One(select = "com.example.homework003.repository.AuthorRepository.getAuthorById")
                    ),
                    @Result(property = "categories", column = "book_id",
                            many = @Many(select = "com.example.homework003.repository.CategoryRepository.getCategoryByBookId")
                    )
            }
    )
    List<Book> findAllBook();

    @Select("SELECT * FROM books WHERE book_id=#{bookId}")
    @ResultMap("bookMapper")
    Book getBookById(Integer bookId);

    @Delete("DELETE FROM books WHERE book_id=#{bookId}")
    boolean deleteBook(Integer bookId);

    @Select("INSERT INTO books (title,published_date, author_id) "+
            "VALUES (#{request.title},#{request.publishedDate}, #{request.authorId}) "+
            "RETURNING book_id"
    )
    Integer saveBook(@Param("request")BookRequest bookRequest);

    @Select("INSERT INTO book_details (book_id, category_id) "+
            "VALUES (#{bookId},#{categoryId})"
    )
    void saveCategoryByBookId(Integer bookId, Integer categoryId);

    @Update("UPDATE books"+
            " SET published_date = #{request.publishedDate},"+
            " title = #{request.title},"+
            " author_id = #{request.authorId}"+
            " WHERE book_id = #{bookId}")
//    @ResultMap("bookMapper")
    Integer updateBook(@Param("request") BookRequest bookRequest,Integer bookId);

    @Delete("DELETE FROM book_details WHERE book_id = #{bookId}")
    void deleteBookInBookDetailById(Integer bookId);

}
