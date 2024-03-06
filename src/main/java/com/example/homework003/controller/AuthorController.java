package com.example.homework003.controller;

import com.example.homework003.model.entity.Author;
import com.example.homework003.model.request.AuthorRequest;
import com.example.homework003.model.response.CustomResponse;
import com.example.homework003.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
//import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC; // Import MDC class
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);
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
//        log.info(String.valueOf(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author by id")
    public ResponseEntity<CustomResponse<Author>> getAuthorById(@PathVariable("id") Integer authorId,
                                                                HttpServletRequest request) {
        // Extracting IP address of the requester
        String ipAddress = request.getRemoteAddr();

        // Extracting request route
        String route = request.getRequestURI();

        // Extracting request field (here, 'id' from path variable)
        String field = String.valueOf(authorId);

        // Put custom fields into MDC
        MDC.put("type", "Requesting");
        MDC.put("ip", ipAddress);
        MDC.put("route", route);
        MDC.put("field", field);

        // Log the request details
        log.info("Request received: IP={}, Route={}, Field={}", ipAddress, route, field);

        CustomResponse<Author> response;
        Author author = authorService.getAuthorById(authorId);
        if (author != null) {
            MDC.put("type", "Response");
            MDC.get("ip");  MDC.get("route"); MDC.get("field");
            // Log the successful response details
            log.info("Response sent:  Author={}", author);
            response = CustomResponse.<Author>builder()
                    .message("Get author by id successfully!")
                    .payload(author)
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();

            return ResponseEntity.ok(response);
        } else {

            // Log the failure to find the author
            log.warn("Author with id={} not found. IP={}, Route={}, Field={}", authorId, ipAddress, route, field);

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
