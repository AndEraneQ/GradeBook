package com.troja.GradeBook.dto.requests;

import com.troja.GradeBook.dto.ClassroomDto;
import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.entity.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditClassRequest {

    @Valid
    private ClassroomDto classroomDto;

    private List<@Valid UserDto> students;
}
