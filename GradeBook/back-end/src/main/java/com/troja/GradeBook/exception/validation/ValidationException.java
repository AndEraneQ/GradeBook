package com.troja.GradeBook.exception.validation;

import com.troja.GradeBook.exception.AppException;

public class ValidationException extends AppException {
    public ValidationException(String message) {
        super(message);
    }
}
