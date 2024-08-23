package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class TeacherMapper {

    public TeacherDto toDto(Teacher teacher) {
        if (teacher == null) {
            return null;
        }

        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());

        if (teacher.getUser() != null) {
            dto.setEmail(teacher.getUser().getEmail());
            dto.setFirstName(teacher.getUser().getFirstName());
            dto.setLastName(teacher.getUser().getLastName());
        }

        return dto;
    }

    public Teacher toEntity(TeacherDto dto) {
        if (dto == null) {
            return null;
        }

        Teacher teacher = new Teacher();
        teacher.setId(dto.getId());

        if (dto.getEmail() != null) {
            User user = new User();
            user.setEmail(dto.getEmail());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            teacher.setUser(user);
        }

        return teacher;
    }
}
