package com.troja.GradeBook.dto.requests;

import com.troja.GradeBook.dto.TeacherDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddClassRequest {

    @NotBlank(message = "Class name cannot be empty")
    private String className;

    @Valid
    @NotNull(message = "Teacher cannot be null")
    private TeacherDto teacher;
}
