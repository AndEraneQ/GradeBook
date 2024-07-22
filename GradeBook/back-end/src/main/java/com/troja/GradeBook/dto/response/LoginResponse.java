package com.troja.GradeBook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private String jwtToken;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;
}
