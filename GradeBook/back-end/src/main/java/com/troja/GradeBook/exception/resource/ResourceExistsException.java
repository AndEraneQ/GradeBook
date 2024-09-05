package com.troja.GradeBook.exception.resource;

import com.troja.GradeBook.exception.AppException;

public class ResourceExistsException extends AppException {
    public ResourceExistsException(String message) {
        super(message);
    }
}
