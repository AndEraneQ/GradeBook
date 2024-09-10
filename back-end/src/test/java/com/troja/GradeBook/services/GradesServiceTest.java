package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.GradeDto;
import com.troja.GradeBook.dto.requests.AddGradeRequest;
import com.troja.GradeBook.entity.Grade;
import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.exception.resource.ResourceNotFoundException;
import com.troja.GradeBook.mapper.GradeMapper;
import com.troja.GradeBook.repository.GradeRepository;
import com.troja.GradeBook.repository.SubjectRepository;
import com.troja.GradeBook.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GradesServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private GradeMapper gradeMapper;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GradesService gradesService;

    @Test
    void testFindByUserIdAndSubjectId_ReturnsGradeDtos() {
        Long userId = 1L;
        Long subjectId = 1L;

        Grade grade1 = new Grade();
        grade1.setId(1L);
        grade1.setValue(5);

        Grade grade2 = new Grade();
        grade2.setId(2L);
        grade2.setValue(4);

        List<Grade> grades = Arrays.asList(grade1, grade2);

        GradeDto gradeDto1 = new GradeDto();
        gradeDto1.setId(1L);
        gradeDto1.setValue(5);

        GradeDto gradeDto2 = new GradeDto();
        gradeDto2.setId(2L);
        gradeDto2.setValue(4);

        List<GradeDto> gradeDtos = Arrays.asList(gradeDto1, gradeDto2);

        when(gradeRepository.findByUserIdAndSubjectId(userId, subjectId)).thenReturn(grades);
        when(gradeMapper.toDto(grade1)).thenReturn(gradeDto1);
        when(gradeMapper.toDto(grade2)).thenReturn(gradeDto2);

        ResponseEntity<List<GradeDto>> response = gradesService.findByUserIdAndSubjectId(userId, subjectId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(gradeDtos, response.getBody());

        verify(gradeRepository, times(1)).findByUserIdAndSubjectId(userId, subjectId);
        verify(gradeMapper, times(1)).toDto(grade1);
        verify(gradeMapper, times(1)).toDto(grade2);
    }

    @Test
    void testFindByUserIdAndSubjectId_ReturnsEmptyList() {
        Long userId = 1L;
        Long subjectId = 1L;

        when(gradeRepository.findByUserIdAndSubjectId(userId, subjectId)).thenReturn(List.of());

        ResponseEntity<List<GradeDto>> response = gradesService.findByUserIdAndSubjectId(userId, subjectId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(0, response.getBody().size());

        verify(gradeRepository, times(1)).findByUserIdAndSubjectId(userId, subjectId);
    }

    @Test
    void testEditGrade_Success() {
        GradeDto gradeDto = new GradeDto();
        gradeDto.setId(1L);
        gradeDto.setValue(5);
        gradeDto.setDescription("new grade");

        Grade grade = new Grade();
        grade.setId(1L);
        grade.setValue(3);
        grade.setDescription("old grade");

        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));

        ResponseEntity<String> response = gradesService.editGrade(gradeDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Grade updated successfully!", response.getBody());
        assertEquals(5, grade.getValue());
        assertEquals("new grade", grade.getDescription());
        assertNotNull(grade.getDate());

        verify(gradeRepository, times(1)).findById(1L);
        verify(gradeRepository, times(1)).save(grade);
    }

    @Test
    void testEditGrade_GradeNotFound() {
        GradeDto gradeDto = new GradeDto();
        gradeDto.setId(1L);

        when(gradeRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            gradesService.editGrade(gradeDto);
        });

        assertEquals("Grade not found with id: 1", exception.getMessage());

        verify(gradeRepository, times(1)).findById(1L);
        verify(gradeRepository, times(0)).save(any(Grade.class));
    }

    @Test
    void testAddGrade_Success() {
        AddGradeRequest addGradeRequest = new AddGradeRequest();
        addGradeRequest.setStudentId(1L);
        addGradeRequest.setSubjectId(2L);
        addGradeRequest.setValue(5);
        addGradeRequest.setDescription("test grade");

        User user = new User();
        user.setId(1L);

        Subject subject = new Subject();
        subject.setId(2L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(subjectRepository.findById(2L)).thenReturn(Optional.of(subject));

        ResponseEntity<String> response = gradesService.addGrade(addGradeRequest);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
        assertEquals("Grade added successfully!", response.getBody());

        verify(userRepository, times(1)).findById(1L);
        verify(subjectRepository, times(1)).findById(2L);
        verify(gradeRepository, times(1)).save(any(Grade.class));
    }

    @Test
    void testAddGrade_UserNotFound() {
        AddGradeRequest addGradeRequest = new AddGradeRequest();
        addGradeRequest.setStudentId(1L);
        addGradeRequest.setSubjectId(2L);
        addGradeRequest.setValue(5);
        addGradeRequest.setDescription("test grade");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            gradesService.addGrade(addGradeRequest);
        });

        assertEquals("User not found with id: 1", exception.getMessage());

        verify(userRepository, times(1)).findById(1L);
        verify(subjectRepository, times(0)).findById(anyLong());
        verify(gradeRepository, times(0)).save(any(Grade.class));
    }

    @Test
    void testAddGrade_SubjectNotFound() {
        AddGradeRequest addGradeRequest = new AddGradeRequest();
        addGradeRequest.setStudentId(1L);
        addGradeRequest.setSubjectId(2L);
        addGradeRequest.setValue(5);
        addGradeRequest.setDescription("test grade");

        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(subjectRepository.findById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            gradesService.addGrade(addGradeRequest);
        });

        assertEquals("Subject not found with id: 2", exception.getMessage());

        verify(userRepository, times(1)).findById(1L);
        verify(subjectRepository, times(1)).findById(2L);
        verify(gradeRepository, times(0)).save(any(Grade.class));
    }

    @Test
    void testDeleteGrade_Success() {
        Long gradeId = 1L;

        when(gradeRepository.existsById(gradeId)).thenReturn(true);

        ResponseEntity<String> response = gradesService.deleteGrade(gradeId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Grade deleted successfully!", response.getBody());

        verify(gradeRepository, times(1)).existsById(gradeId);
        verify(gradeRepository, times(1)).deleteById(gradeId);
    }

    @Test
    void testDeleteGrade_GradeNotFound() {
        Long gradeId = 1L;

        when(gradeRepository.existsById(gradeId)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            gradesService.deleteGrade(gradeId);
        });

        assertEquals("Grade not found with id: 1", exception.getMessage());

        verify(gradeRepository, times(1)).existsById(gradeId);
        verify(gradeRepository, times(0)).deleteById(anyLong());
    }
}
