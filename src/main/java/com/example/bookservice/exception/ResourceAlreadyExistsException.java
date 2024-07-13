package com.example.bookservice.exception;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String userName){
        super("User with username: " + userName + " already exists");
    }
}
