package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.MailDto;
import com.troja.GradeBook.entity.Mail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MailMapper {

    MailMapper INSTANCE = Mappers.getMapper(MailMapper.class);

    @Mapping(source = "fromUser.email", target = "fromUserEmail")
    @Mapping(source = "toUser.email", target = "toUserEmail")
    MailDto toDto(Mail mail);

    @Mapping(source = "fromUserEmail", target = "fromUser.email")
    @Mapping(source = "toUserEmail", target = "toUser.email")
    Mail toEntity(MailDto mailDto);
}
