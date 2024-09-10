package com.troja.GradeBook.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GradeDto {

    @NotNull(message = "Grade ID cannot be null")
    private Long id;

    @Min(value = 1, message = "Grade value must be at least 1")
    @Max(value = 6, message = "Grade value must not exceed 6")
    private int value;

    @PastOrPresent(message = "Date must be in the past or present")
    private Date date;

    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;
}