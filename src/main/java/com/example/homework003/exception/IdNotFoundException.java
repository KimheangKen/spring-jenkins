package com.example.homework003.exception;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String type, Integer id){
        super(type+" with id : "+ id + " not found");
    }


}
