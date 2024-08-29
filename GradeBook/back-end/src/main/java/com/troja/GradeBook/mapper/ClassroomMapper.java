package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.ClassroomDto;
import com.troja.GradeBook.entity.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class})
public interface ClassroomMapper {

    ClassroomMapper INSTANCE = Mappers.getMapper(ClassroomMapper.class);

    @Mapping(source = "teacher", target = "teacherDto")
    ClassroomDto toDto(Classroom classroom);

    @Mapping(source = "teacherDto", target = "teacher")
    Classroom toEntity(ClassroomDto classroomDto);
}