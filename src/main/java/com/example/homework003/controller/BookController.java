package com.example.homework003.controller;

import com.example.homework003.model.entity.Book;
import com.example.homework003.model.request.BookRequest;
import com.example.homework003.model.response.CustomResponse;
import com.example.homework003.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
//import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Get all books")
    public ResponseEntity<List<Book>> getAllBook(){
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Book by ID")
    public ResponseEntity<CustomResponse<Book>> getBookById(@PathVariable("id") Integer bookId ){

        CustomResponse<Book> response;
        if(bookService.getBookById(bookId) != null){
            response = CustomResponse.<Book>builder()
                    .message("Get customer by id successfully!")
                    .payload(bookService.getBookById(bookId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);

        }else{

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book by id")
    public ResponseEntity<CustomResponse<String>> deleteBookById(@PathVariable("id") Integer bookId){
        CustomResponse<String> response;
        if(bookService.deleteBook(bookId)){
            response = CustomResponse.<String>builder()
                    .message("Delete this book is successful !!")
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    @Operation(summary = "Add new book")
    public ResponseEntity<CustomResponse<Book>> addNewBook(@RequestBody BookRequest bookRequest){
        CustomResponse<Book> response;
        Integer storeBookId = bookService.saveBook(bookRequest);
        if(storeBookId != null){
            response = CustomResponse.<Book>builder()
                    .message("Create invoice successfully!!")
                    .payload(bookService.getBookById(storeBookId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Book by Id")
    public ResponseEntity<CustomResponse<Book>> updateInvoiceById(
            @RequestBody BookRequest bookRequest, @PathVariable("id") Integer bookId
    ){
        CustomResponse<Book> response;
        Integer idBookUpdate = bookService.updateBook(bookRequest,bookId);
        if(idBookUpdate == 1){
            response = CustomResponse.<Book>builder()
                    .message("Update this book Successfully!")
                    .payload(bookService.getBookById(bookId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return  ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
