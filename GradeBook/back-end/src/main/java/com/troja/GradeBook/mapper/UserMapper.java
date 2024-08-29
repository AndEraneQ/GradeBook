package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "classroom.name", target = "className")
    UserDto toDto(User user);

    @Mapping(target = "classroom", ignore = true)
    User toEntity(UserDto userDto);
}
