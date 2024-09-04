package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.GradeDto;
import com.troja.GradeBook.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "value", target = "value"),
            @Mapping(source = "date", target = "date")
    })
    GradeDto toDto(Grade grade);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "value", target = "value"),
            @Mapping(source = "date", target = "date")
    })
    Grade toEntity(GradeDto gradeDto);
}
