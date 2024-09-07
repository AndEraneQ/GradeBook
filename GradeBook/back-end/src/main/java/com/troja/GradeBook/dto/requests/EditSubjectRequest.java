package com.troja.GradeBook.dto.requests;

import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.entity.Subject;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditSubjectRequest {

    @Valid
    private Subject subject;

    private List<@Valid TeacherDto> deletedTeachers;

    private List<@Valid TeacherDto> addedTeachers;
}
