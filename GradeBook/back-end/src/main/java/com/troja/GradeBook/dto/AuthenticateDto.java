package com.troja.GradeBook.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateDto {
    private String email;
    private String password;
}
