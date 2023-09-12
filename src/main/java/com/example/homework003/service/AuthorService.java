package com.example.homework003.service;

import com.example.homework003.model.entity.Author;
import com.example.homework003.model.request.AuthorRequest;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthor();

    Author getAuthorById(Integer authorId);

    boolean deleteAuthorById(Integer authorId);

    Integer addNewAuthor(AuthorRequest authorRequest);

    Integer updateAuthor(AuthorRequest authorRequest, Integer authorId);
}
