package com.troja.GradeBook.dto;

import com.troja.GradeBook.entity.Role;
import com.troja.GradeBook.entity.Teacher;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    @NotNull(message = "Subject ID cannot be null")
    private Long id;

    @NotBlank(message = "Subject name cannot be blank")
    private String name;

    @Valid
    private Set<TeacherDto> teachersDto;
}