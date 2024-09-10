package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.TeacherSubjectClassDto;
import com.troja.GradeBook.entity.TeacherSubjectClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherSubjectClassMapper {

    TeacherSubjectClassMapper INSTANCE = Mappers.getMapper(TeacherSubjectClassMapper.class);

    @Mapping(target = "subjectId", source = "subject.id")
    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "classId", source = "classroom.id")
    TeacherSubjectClassDto toDto(TeacherSubjectClass teacherSubjectClass);

    @Mapping(target = "subject.id", source = "subjectId")
    @Mapping(target = "teacher.id", source = "teacherId")
    @Mapping(target = "classroom.id", source = "classId")
    TeacherSubjectClass toEntity(TeacherSubjectClassDto dto);
}
