package com.troja.GradeBook.dto.requests;

import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.dto.UserDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @Valid
    private UserDto user;

    @Valid
    private ResidenceDto residence;
}
