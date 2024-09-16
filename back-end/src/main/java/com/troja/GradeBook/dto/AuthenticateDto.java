package com.troja.GradeBook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateDto {

    @NotEmpty(message = "Email cannot be empty!")
    @Email(message = "Email not valid!")
    private String email;

    @NotEmpty(message = "Password cannot be empty!")
    private String password;
}