package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.entity.Residence;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ResidenceMapper {

    ResidenceMapper INSTANCE = Mappers.getMapper(ResidenceMapper.class);

    ResidenceDto toDto(Residence residence);

    Residence toEntity(ResidenceDto residenceDto);
}
