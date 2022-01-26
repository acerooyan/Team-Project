package com.example.emrestserver.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String s) {
        super(s);
    }
}