package com.example.homework003.service.serviceImp;

import com.example.homework003.exception.CheckException;
import com.example.homework003.exception.IdNotFoundException;
import com.example.homework003.model.entity.Author;
import com.example.homework003.model.request.AuthorRequest;
import com.example.homework003.repository.AuthorRepository;
import com.example.homework003.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImp implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImp(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAllAuthor();
    }

    @Override
    public Author getAuthorById(Integer authorId) {
            Author authorById = authorRepository.getAuthorById(authorId);
            if(authorById == null){
                throw new IdNotFoundException("Author",authorId);
            }else return authorById;

    }

    @Override
    public boolean deleteAuthorById(Integer authorId) {
        boolean isDeleteAuthorById = authorRepository.deleteAuthorById(authorId);
        if(isDeleteAuthorById){
            return isDeleteAuthorById;
        }else throw new IdNotFoundException("Author",authorId);

    }

    @Override
    public Integer addNewAuthor(AuthorRequest authorRequest) {

        if((authorRequest.getAuthorName().trim().isEmpty() || authorRequest.getGender().trim().isEmpty())){
            throw new CheckException("field can not empty");

        }else if (!(authorRequest.getGender().equalsIgnoreCase("male") || authorRequest.getGender().equalsIgnoreCase("female"))) {
            throw new CheckException("gender should be male or female");
        }else {
            Integer authorId =authorRepository.saveAuthor(authorRequest);
            return authorId;
        }

    }
        @Override
        public Integer updateAuthor (AuthorRequest authorRequest, Integer authorId){
            // search author
            Author getAuthorById = authorRepository.getAuthorById(authorId);

            if(getAuthorById == null){
                throw new IdNotFoundException("Author",authorId);

            }else if((authorRequest.getAuthorName().trim().isEmpty() || authorRequest.getGender().trim().isEmpty())){
                throw new CheckException("field can not empty");
            }
            else if (!(authorRequest.getGender().equalsIgnoreCase("male") || authorRequest.getGender().equalsIgnoreCase("female"))) {
                throw new CheckException("gender should be male or female");
            } else {
                Integer authorIdUpdate = authorRepository.updateAuthor(authorRequest, authorId);
                return authorIdUpdate;
            }
        }
    }

