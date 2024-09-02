package com.troja.GradeBook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectAndClassroomDto {
    private ClassroomDto classroomDto;
    private SubjectDto subjectDto;
}
