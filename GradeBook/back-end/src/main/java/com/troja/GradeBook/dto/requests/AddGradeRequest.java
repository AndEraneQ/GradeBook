package com.troja.GradeBook.dto.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddGradeRequest {

    @NotNull(message = "Student ID cannot be null")
    private Long studentId;

    @NotNull(message = "Subject ID cannot be null")
    private Long subjectId;

    @Min(value = 1, message = "Grade must be at least 1")
    @Max(value = 6, message = "Grade must be no more than 6")
    private int value;

    @NotBlank(message = "Description cannot be blank")
    private String description;
}