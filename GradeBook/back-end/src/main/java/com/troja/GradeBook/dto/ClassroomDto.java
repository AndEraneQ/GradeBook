package com.troja.GradeBook.dto;

import com.troja.GradeBook.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClassroomDto {
    private Long id;
    private String name;
    private TeacherDto teacherDto;
}
