package com.example.homework003.controller;

import com.example.homework003.model.entity.Author;
import com.example.homework003.model.request.AuthorRequest;
import com.example.homework003.model.response.CustomResponse;
import com.example.homework003.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @Operation(summary = "Get all author")
    public ResponseEntity<CustomResponse<List<Author>>> getAllAuthor(){

        CustomResponse<List<Author>> response = CustomResponse.<List<Author>>builder()
                .message("Get all authors successfully!")
                .payload(authorService.getAllAuthor())
                .httpStatus(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author by id")
    public ResponseEntity<CustomResponse<Author>> getAuthorById(@PathVariable("id") Integer authorId){
        CustomResponse<Author> response;
        if(authorService.getAuthorById(authorId) != null){
            response = CustomResponse.<Author>builder()
                    .message("Get customer by id successfully!")
                    .payload(authorService.getAuthorById(authorId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);

        }else{

            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete author by id")
    public ResponseEntity<CustomResponse<String>> deleteAuthorById(@PathVariable("id") Integer authorId){
        CustomResponse<String> response;
        if(authorService.deleteAuthorById(authorId)){
            response = CustomResponse.<String>builder()
                    .message("Delete successfully")
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    @Operation(summary = "Save new author")
    public ResponseEntity<CustomResponse<Author>> addNewAuthor( @RequestBody AuthorRequest authorRequest){
        Integer storeAuthorId = authorService.addNewAuthor(authorRequest);
        if(storeAuthorId != null){
            CustomResponse<Author> response = CustomResponse.<Author>builder()
                    .message("Add new author successfully")
                    .payload(authorService.getAuthorById(storeAuthorId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return null;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update author by Id")
    public ResponseEntity<CustomResponse<Author>> updateAuthorById(
            @RequestBody AuthorRequest authorRequest, @PathVariable("id") Integer authorId
    ){
        CustomResponse<Author> response;
        Integer idAuthorUpdate = authorService.updateAuthor(authorRequest,authorId);
        if(idAuthorUpdate != null){
            response = CustomResponse.<Author>builder()
                    .message("Updated author by Id Successfully!")
                    .payload(authorService.getAuthorById(idAuthorUpdate))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();

            return  ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }
    }



}
