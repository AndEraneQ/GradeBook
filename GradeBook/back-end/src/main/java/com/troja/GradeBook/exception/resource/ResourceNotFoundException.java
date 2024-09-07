package com.troja.GradeBook.exception.resource;

import com.troja.GradeBook.exception.AppException;

public class ResourceNotFoundException extends AppException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
