package com.learn.spring.boot.web.springbootweb.advices;

import lombok.Data;

@Data
public class ErrorResponse<T> extends ApiResponse<T>{

    private T error;

    public ErrorResponse(T error) {
        this.error = error;
    }
}
