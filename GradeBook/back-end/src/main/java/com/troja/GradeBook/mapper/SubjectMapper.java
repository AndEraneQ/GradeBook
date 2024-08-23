package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.SubjectDto;
import com.troja.GradeBook.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectDto toDto(Subject subject);

    Subject toEntity(SubjectDto subjectDto);
}
