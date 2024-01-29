//package com.example.homework003.exception;
//
//import org.springframework.http.HttpStatus;
////import org.springframework.http.ProblemDetail;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.net.URI;
//import java.time.Instant;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(CheckException.class)
//    ProblemDetail handleCheckException(CheckException e){
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
//        problemDetail.setTitle("Invalid Input");
//        problemDetail.setType(URI.create("http:localhost:8080/errors/not-found"));
//        problemDetail.setProperty("timestamp", Instant.now());
//        return problemDetail;
//    }
//
//    @ExceptionHandler(IdNotFoundException.class)
//    ProblemDetail handleIdNotFound(IdNotFoundException e){
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
//        problemDetail.setTitle("Invalid Input");
//        problemDetail.setType(URI.create("http:localhost:8080/errors/not-found"));
//        problemDetail.setProperty("timestamp", Instant.now());
//        return problemDetail;
//    }
//}
