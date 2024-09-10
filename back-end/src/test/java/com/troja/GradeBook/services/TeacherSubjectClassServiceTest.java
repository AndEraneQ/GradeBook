package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.ClassroomDto;
import com.troja.GradeBook.dto.SubjectAndClassroomDto;
import com.troja.GradeBook.dto.SubjectDto;
import com.troja.GradeBook.dto.TeacherSubjectClassDto;
import com.troja.GradeBook.entity.Classroom;
import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.entity.TeacherSubjectClass;
import com.troja.GradeBook.mapper.ClassroomMapper;
import com.troja.GradeBook.mapper.SubjectMapper;
import com.troja.GradeBook.mapper.TeacherSubjectClassMapper;
import com.troja.GradeBook.repository.ClassroomRepository;
import com.troja.GradeBook.repository.SubjectRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import com.troja.GradeBook.repository.TeacherSubjectClassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherSubjectClassServiceTest {

    @Mock
    private TeacherSubjectClassRepository teacherSubjectClassRepository;

    @Mock
    private TeacherSubjectClassMapper teacherSubjectClassMapper;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private ClassroomRepository classroomRepository;

    @Mock
    private ClassroomMapper classroomMapper;

    @Mock
    private SubjectMapper subjectMapper;

    @InjectMocks
    private TeacherSubjectClassService teacherSubjectClassService;

    @Test
    void findConnectionByClassIdTestReturnTeacherSubjectClassDtos(){
        Long classId = 1L;
        Classroom classroom = new Classroom();
        classroom.setId(classId);

        TeacherSubjectClass teacherSubjectClass = new TeacherSubjectClass(1L,new Teacher(),new Subject(),classroom);
        TeacherSubjectClassDto teacherSubjectClassDto = new TeacherSubjectClassDto(1L,1L,1L);

        when(teacherSubjectClassRepository.findByClassroomId(classId)).thenReturn(List.of(teacherSubjectClass));
        when(teacherSubjectClassMapper.toDto(teacherSubjectClass)).thenReturn(teacherSubjectClassDto);

        ResponseEntity<List<TeacherSubjectClassDto>> response = teacherSubjectClassService.findConnectionByClassId(classId);

        assertEquals(List.of(teacherSubjectClassDto),response.getBody());
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void testGetAllClassesAndSubjectsLearningByTeacher() {
        Long teacherId = 1L;
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);

        TeacherSubjectClass teacherSubjectClass = new TeacherSubjectClass();

        Classroom classroom = new Classroom();
        ClassroomDto classroomDto = new ClassroomDto();

        Subject subject = new Subject();
        SubjectDto subjectDto = new SubjectDto();

        teacherSubjectClass.setClassroom(classroom);
        teacherSubjectClass.setSubject(subject);
        teacherSubjectClass.setTeacher(teacher);

        when(teacherSubjectClassRepository.findByTeacherId(teacherId))
                .thenReturn(Collections.singletonList(teacherSubjectClass));


        SubjectAndClassroomDto subjectAndClassroomDto = new SubjectAndClassroomDto(classroomDto,subjectDto);
        when(classroomMapper.toDto(classroom)).thenReturn(classroomDto);
        when(subjectMapper.toDto(subject)).thenReturn(subjectDto);

        ResponseEntity<List<SubjectAndClassroomDto>> response = teacherSubjectClassService.getAllClassesAndSubjectsLearningByTeacher(teacherId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1,response.getBody().size());

        verify(teacherSubjectClassRepository, times(1)).findByTeacherId(teacherId);
        verify(classroomMapper, times(1)).toDto(classroom);
        verify(subjectMapper, times(1)).toDto(subject);
    }

    @Test
    void testUpdateConnection_RemoveTeacherAssignment() {
        TeacherSubjectClassDto dto = new TeacherSubjectClassDto();
        dto.setClassId(1L);
        dto.setSubjectId(2L);
        dto.setTeacherId(null);

        TeacherSubjectClass existingAssignment = new TeacherSubjectClass();
        existingAssignment.setId(3L);

        when(teacherSubjectClassRepository.findByClassroomIdAndSubjectId(dto.getClassId(), dto.getSubjectId()))
                .thenReturn(Optional.of(existingAssignment));

        ResponseEntity<String> response = teacherSubjectClassService.updateConnection(dto);

        assertEquals("Teacher assignment removed successfully", response.getBody());

        verify(teacherSubjectClassRepository, times(1)).delete(existingAssignment);
    }

    @Test
    void testUpdateConnection_UpdateTeacherAssignment() {
        TeacherSubjectClassDto dto = new TeacherSubjectClassDto();
        dto.setClassId(1L);
        dto.setSubjectId(2L);
        dto.setTeacherId(3L);

        TeacherSubjectClass existingAssignment = new TeacherSubjectClass();

        Teacher teacher = new Teacher();
        teacher.setId(3L);
        Classroom classroom = new Classroom();
        Subject subject = new Subject();

        when(teacherSubjectClassRepository.findByClassroomIdAndSubjectId(dto.getClassId(), dto.getSubjectId()))
                .thenReturn(Optional.of(existingAssignment));
        when(teacherRepository.findById(dto.getTeacherId())).thenReturn(Optional.of(teacher));
        when(classroomRepository.findById(dto.getClassId())).thenReturn(Optional.of(classroom));
        when(subjectRepository.findById(dto.getSubjectId())).thenReturn(Optional.of(subject));

        ResponseEntity<String> response = teacherSubjectClassService.updateConnection(dto);

        assertEquals("Updated Teacher successfully", response.getBody());

        verify(teacherSubjectClassRepository, times(1)).save(existingAssignment);
    }

    @Test
    void testUpdateConnection_NoChangesMade() {
        TeacherSubjectClassDto dto = new TeacherSubjectClassDto();
        dto.setClassId(1L);
        dto.setSubjectId(2L);
        dto.setTeacherId(null);

        when(teacherSubjectClassRepository.findByClassroomIdAndSubjectId(dto.getClassId(), dto.getSubjectId()))
                .thenReturn(Optional.empty());

        ResponseEntity<String> response = teacherSubjectClassService.updateConnection(dto);

        assertEquals("No changes made", response.getBody());

        verify(teacherSubjectClassRepository, never()).delete(any());
        verify(teacherSubjectClassRepository, never()).save(any());
    }

    @Test
    void testUpdateConnection_TeacherNotFound() {
        TeacherSubjectClassDto dto = new TeacherSubjectClassDto();
        dto.setClassId(1L);
        dto.setSubjectId(2L);
        dto.setTeacherId(3L);

        TeacherSubjectClass existingAssignment = new TeacherSubjectClass();

        when(teacherSubjectClassRepository.findByClassroomIdAndSubjectId(dto.getClassId(), dto.getSubjectId()))
                .thenReturn(Optional.of(existingAssignment));
        when(teacherRepository.findById(dto.getTeacherId())).thenReturn(Optional.empty());

        try {
            teacherSubjectClassService.updateConnection(dto);
        } catch (RuntimeException ex) {
            assertEquals("Teacher not found", ex.getMessage());
        }

        verify(teacherSubjectClassRepository, never()).save(any());
    }
}
