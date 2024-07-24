package com.troja.GradeBook.security;

import com.troja.GradeBook.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String firstName;
    private String lastName;
    private String role;
}
