package com.troja.GradeBook.dto;

import com.troja.GradeBook.entity.Role;
import com.troja.GradeBook.entity.Teacher;
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
    private Long id;
    private String name;
    private Role role;
    Set<TeacherDto> teachersDto;
}
