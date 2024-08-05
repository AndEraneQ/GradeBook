package com.troja.GradeBook.dto;

import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.entity.User;
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
    private Subject subject;
    private List<UserDto> deletedTeachers;
    private List<UserDto> addedTeachers;
}
