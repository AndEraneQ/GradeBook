package com.troja.GradeBook.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddGradeRequest {
    private Long studentId;
    private Long subjectId;
    private int value;
    private String description;
}
