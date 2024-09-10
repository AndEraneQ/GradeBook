package com.troja.GradeBook.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectAndClassroomDto {

    @Valid
    private ClassroomDto classroomDto;

    @Valid
    private SubjectDto subjectDto;
}
