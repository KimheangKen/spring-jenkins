package com.example.homework003.service;

import com.example.homework003.model.entity.Book;
import com.example.homework003.model.request.BookRequest;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();

    Book getBookById(Integer bookId);

    boolean deleteBook(Integer bookId);

    Integer saveBook(BookRequest bookRequest);

    Integer updateBook(BookRequest bookRequest, Integer bookId);
}
