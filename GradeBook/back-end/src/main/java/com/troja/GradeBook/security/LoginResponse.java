package com.troja.GradeBook.security;

import com.troja.GradeBook.entity.Role;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String token;
    private String firstName;
    private String lastName;
    private String email;
    private String className;
    private Long classId;
    private Role role;
}
