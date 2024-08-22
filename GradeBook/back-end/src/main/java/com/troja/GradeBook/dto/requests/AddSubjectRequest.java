package com.troja.GradeBook.dto.requests;

import com.troja.GradeBook.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddSubjectRequest {
    private String name;
    private List<UserDto> listOfTeachers;
}
