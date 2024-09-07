package com.troja.GradeBook.dto;

import com.troja.GradeBook.entity.Teacher;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClassroomDto {

    @NotNull(message = "Classroom ID cannot be null")
    private Long id;

    @NotEmpty(message = "Classroom name cannot be empty")
    @Size(max = 50, message = "Classroom name cannot exceed 50 characters")
    private String name;

    @Valid
    private TeacherDto teacherDto;
}
