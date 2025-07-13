package com.learn.spring.boot.web.springbootweb.advices;

import lombok.Data;

@Data
public class SuccessResponse<T> extends ApiResponse<T> {
    private final T data;

    public SuccessResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}