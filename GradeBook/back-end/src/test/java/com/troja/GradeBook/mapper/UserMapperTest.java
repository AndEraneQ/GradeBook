package com.troja.GradeBook.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.entity.Role;
import com.troja.GradeBook.entity.User;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    @Test
    void shouldMapUserToUserDto() {
        // given
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setRole(Role.STUDENT);

        // when
        UserDto userDto = UserMapper.INSTANCE.toDto(user);

        // then
        assertThat(userDto).isNotNull();
        assertThat(userDto.getId()).isEqualTo(user.getId());
        assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
        assertThat(userDto.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(userDto.getLastName()).isEqualTo(user.getLastName());
        assertThat(userDto.getRole()).isEqualTo(user.getRole());
    }

    @Test
    void shouldMapUserDtoToUser() {
        // given
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("test@example.com");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setRole(Role.STUDENT);

        // when
        User user = UserMapper.INSTANCE.toEntity(userDto);

        // then
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(userDto.getId());
        assertThat(user.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(user.getFirstName()).isEqualTo(userDto.getFirstName());
        assertThat(user.getLastName()).isEqualTo(userDto.getLastName());
        assertThat(user.getRole()).isEqualTo(userDto.getRole());
    }

    @Test
    void shouldReturnNullWhenMappingNullUserToUserDto() {
        // when
        UserDto userDto = UserMapper.INSTANCE.toDto(null);

        // then
        assertThat(userDto).isNull();
    }

    @Test
    void shouldReturnNullWhenMappingNullUserDtoToUser() {
        // when
        User user = UserMapper.INSTANCE.toEntity(null);

        // then
        assertThat(user).isNull();
    }
}