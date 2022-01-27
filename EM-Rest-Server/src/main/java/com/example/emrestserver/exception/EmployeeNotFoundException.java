package com.example.emrestserver.exception;

public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException(String s) {
        super(s);
    }
}