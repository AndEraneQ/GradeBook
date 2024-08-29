package com.troja.GradeBook.dto.requests;

import com.troja.GradeBook.dto.TeacherDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddClassRequest {
    private String className;
    private TeacherDto teacher;
}
