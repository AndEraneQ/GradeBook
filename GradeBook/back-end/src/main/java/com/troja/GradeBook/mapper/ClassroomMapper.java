package com.troja.GradeBook.mapper;


import com.troja.GradeBook.dto.ClassroomDto;
import com.troja.GradeBook.entity.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

    ClassroomMapper INSTANCE = Mappers.getMapper(ClassroomMapper.class);

    ClassroomDto toDto(Classroom c);

    Classroom toEntity(ClassroomDto classroomDto);
}
