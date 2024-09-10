package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.SubjectDto;
import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", uses = TeacherMapper.class)
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    @Mapping(target = "teachersDto", source = "teachers")
    SubjectDto toDto(Subject subject);

    @Mapping(target = "teachers", source = "teachersDto")
    Subject toEntity(SubjectDto subjectDto);
}