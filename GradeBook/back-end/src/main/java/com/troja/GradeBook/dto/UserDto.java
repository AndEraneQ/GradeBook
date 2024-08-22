package com.troja.GradeBook.dto;

import com.troja.GradeBook.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}
