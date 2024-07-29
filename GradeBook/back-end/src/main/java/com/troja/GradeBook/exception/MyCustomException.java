package com.troja.GradeBook.exception;

import lombok.Getter;

@Getter
public class MyCustomException extends RuntimeException {
    private final String field;
    private final String message;

    public MyCustomException(String field, String message) {
        super(message);
        this.field = field;
        this.message = message;
    }
}
