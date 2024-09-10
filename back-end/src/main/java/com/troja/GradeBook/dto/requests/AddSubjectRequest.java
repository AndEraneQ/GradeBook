package com.troja.GradeBook.dto.requests;

import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.dto.UserDto;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Subject name cannot be blank")
    private String name;

    private List<TeacherDto> listOfTeachers;
}
