package com.example.homework003.service.serviceImp;

import com.example.homework003.exception.CheckException;
import com.example.homework003.exception.IdNotFoundException;
import com.example.homework003.model.entity.Book;
import com.example.homework003.model.request.BookRequest;
import com.example.homework003.repository.BookRepository;
import com.example.homework003.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService{
    private final BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAllBook();
    }

    @Override
    public Book getBookById(Integer bookId) {
        Book book = bookRepository.getBookById(bookId);
        if(book == null){
            throw new IdNotFoundException("book",bookId);
        }else return book;
    }

    @Override
    public boolean deleteBook(Integer bookId) {
        boolean book = bookRepository.deleteBook(bookId);
        if(!book){
            throw new IdNotFoundException("book",bookId);
        }return book;
    }

    @Override
    public Integer saveBook(BookRequest bookRequest) {

        if(bookRequest.getTitle().trim().isEmpty()){
            throw new CheckException("Field can not empty");
        }else {
            Integer storeBookId = bookRepository.saveBook(bookRequest);

            for (Integer categoryId : bookRequest.getCategoryId()
            ) {
                bookRepository.saveCategoryByBookId(storeBookId, categoryId);
            }
            return storeBookId;
        }
    }

    @Override
    public Integer updateBook(BookRequest bookRequest, Integer bookId) {
        Book getBookById = bookRepository.getBookById(bookId);
        if(getBookById == null){
            throw new IdNotFoundException("book",bookId);
        }else if(bookRequest.getTitle().trim().isEmpty()){
            throw new CheckException("Field can not empty");
        }else {
            Integer storeUpdateBookId = bookRepository.updateBook(bookRequest, bookId);
            if (storeUpdateBookId == 1) {
                bookRepository.deleteBookInBookDetailById(bookId);
                bookRequest.getCategoryId().forEach(categoryId -> bookRepository.saveCategoryByBookId(bookId, categoryId));
            }
            return storeUpdateBookId;
        }
    }

}
