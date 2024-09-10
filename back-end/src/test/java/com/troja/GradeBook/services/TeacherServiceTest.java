package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.mapper.TeacherMapper;
import com.troja.GradeBook.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @InjectMocks
    private TeacherService teacherService;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private TeacherMapper teacherMapper;

    private Teacher teacher;
    private TeacherDto teacherDto;

    @BeforeEach
    void setUp() {
        teacher = new Teacher();
        teacher.setId(1L);

        User user = new User();
        user.setFirstName("John");

        teacher.setUser(user);

        teacherDto = new TeacherDto();
        teacherDto.setId(1L);
        teacherDto.setFirstName("John");
    }

    @Test
    void testGetAllTeachers() {
        when(teacherRepository.findAll()).thenReturn(Arrays.asList(teacher));
        when(teacherMapper.toDto(teacher)).thenReturn(teacherDto);

        ResponseEntity<List<TeacherDto>> response = teacherService.getAllTeachers();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("John", response.getBody().get(0).getFirstName());
    }

    @Test
    void testGetAllTeachersOfSubject() {
        Long subjectId = 1L;
        when(teacherRepository.findTeachersBySubjectId(subjectId)).thenReturn(Arrays.asList(teacher));
        when(teacherMapper.toDto(teacher)).thenReturn(teacherDto);

        ResponseEntity<List<TeacherDto>> response = teacherService.getAllTeachersOfSubject(subjectId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("John", response.getBody().get(0).getFirstName());
    }

    @Test
    void testGetTeacherFromUserId() {
        Long userId = 1L;
        when(teacherRepository.findByUserId(userId)).thenReturn(Optional.of(teacher));
        when(teacherMapper.toDto(teacher)).thenReturn(teacherDto);

        ResponseEntity<TeacherDto> response = teacherService.getTeacherFromUserId(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().getFirstName());;
    }

    @Test
    void testGetTeacherFromUserId_NotFound() {
        Long userId = 2L;
        when(teacherRepository.findByUserId(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            teacherService.getTeacherFromUserId(userId);
        });

        String expectedMessage = "Couldn't find teacher with user ID: " + userId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
