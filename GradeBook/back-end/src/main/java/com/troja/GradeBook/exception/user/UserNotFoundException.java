package com.troja.GradeBook.exception.user;

import com.troja.GradeBook.exception.AppException;

public class UserNotFoundException extends AppException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
