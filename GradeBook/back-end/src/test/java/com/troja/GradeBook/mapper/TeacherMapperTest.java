package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class TeacherMapperTest {

    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    void testToDto_NullTeacher() {
        TeacherDto dto = teacherMapper.toDto(null);
        assertNull(dto, "Mapping null Teacher should return null");
    }

    @Test
    void testToDto_ValidTeacher() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setUser(user);

        // Act
        TeacherDto dto = teacherMapper.toDto(teacher);

        // Assert
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("test@example.com", dto.getEmail());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
    }

    @Test
    void testToDto_TeacherWithoutUser() {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setId(2L);
        teacher.setUser(null); // User is null

        // Act
        TeacherDto dto = teacherMapper.toDto(teacher);

        // Assert
        assertNotNull(dto);
        assertEquals(2L, dto.getId());
        assertNull(dto.getEmail());
        assertNull(dto.getFirstName());
        assertNull(dto.getLastName());
    }

    @Test
    void testToEntity_NullDto() {
        Teacher teacher = teacherMapper.toEntity(null);
        assertNull(teacher, "Mapping null TeacherDto should return null");
    }

    @Test
    void testToEntity_ValidDto() {
        // Arrange
        TeacherDto dto = new TeacherDto();
        dto.setId(3L);
        dto.setEmail("test2@example.com");
        dto.setFirstName("Jane");
        dto.setLastName("Smith");

        // Act
        Teacher teacher = teacherMapper.toEntity(dto);

        // Assert
        assertNotNull(teacher);
        assertEquals(3L, teacher.getId());
        assertNotNull(teacher.getUser());
        assertEquals("test2@example.com", teacher.getUser().getEmail());
        assertEquals("Jane", teacher.getUser().getFirstName());
        assertEquals("Smith", teacher.getUser().getLastName());
    }

    @Test
    void testToEntity_DtoWithoutUserData() {
        // Arrange
        TeacherDto dto = new TeacherDto();
        dto.setId(4L);
        dto.setEmail(null);
        dto.setFirstName(null);
        dto.setLastName(null);

        // Act
        Teacher teacher = teacherMapper.toEntity(dto);

        // Assert
        assertNotNull(teacher);
        assertEquals(4L, teacher.getId());
        assertNull(teacher.getUser());
    }
}
