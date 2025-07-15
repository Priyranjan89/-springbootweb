package com.learn.spring.boot.web.springbootweb.exceptions;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
