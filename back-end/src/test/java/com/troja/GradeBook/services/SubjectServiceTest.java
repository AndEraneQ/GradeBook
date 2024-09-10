package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.SubjectDto;
import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.dto.requests.AddSubjectRequest;
import com.troja.GradeBook.dto.requests.EditSubjectRequest;
import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.exception.resource.ResourceNotFoundException;
import com.troja.GradeBook.mapper.SubjectMapper;
import com.troja.GradeBook.repository.SubjectRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SubjectServiceTest {

    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private SubjectMapper subjectMapper;

    private Subject subject;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subject = new Subject("Mathematics");
        subject.setId(99L);
        teacher = new Teacher(1L,new User(),new HashSet<>(),new HashSet<>());
        Set<Teacher> teachers = new HashSet<>();
        teachers.add(teacher);
        subject.setTeachers(teachers);
    }

    @Test
    void shouldReturnAllSubjects() {
        when(subjectRepository.findAll()).thenReturn(Arrays.asList(subject));

        List<SubjectDto> subjects = subjectService.getAllSubjects();

        assertEquals(1, subjects.size());
        verify(subjectMapper).toDto(subject);
    }

    @Test
    void shouldAddSubjectSuccessfully() {
        TeacherDto teacherDto = new TeacherDto(1L,"testemail@gmail.com","Piotr","Trojan");
        AddSubjectRequest request = new AddSubjectRequest("physics", List.of(teacherDto));

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(subjectRepository.existsByName(any())).thenReturn(false);
        when(subjectRepository.save(any())).thenReturn(subject);

        ResponseEntity<String> response = subjectService.addSubject(request);

        assertEquals("Added subject correctly", response.getBody());
        verify(subjectRepository, times(2)).save(any(Subject.class));
    }

    @Test
    void shouldThrowExceptionWhenAddingSubjectWithEmptyName() {
        AddSubjectRequest request = new AddSubjectRequest("", Collections.emptyList());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subjectService.addSubject(request));
        assertEquals("Subject name cannot be empty.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAddingSubjectWithExistingName() {
        AddSubjectRequest request = new AddSubjectRequest("Math", Collections.emptyList());

        when(subjectRepository.existsByName("Math")).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subjectService.addSubject(request));
        assertEquals("Subject with name '" + request.getName() + "' already exists.", exception.getMessage());
    }

    @Test
    void shouldEditSubjectSuccessfully() {
        Subject newSubjectData = new Subject("Biology");
        newSubjectData.setId(99L);

        Set<Teacher> setOfTeacher = new HashSet<>();
        setOfTeacher.add(teacher);
        newSubjectData.setTeachers(setOfTeacher);

        TeacherDto oldTeacherDto = new TeacherDto(1L,"","","");
        TeacherDto newTeacherDto = new TeacherDto(2L,"","","");

        Teacher newTeacher = new Teacher(2L,new User(),new HashSet<>(),new HashSet<>());

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(teacherRepository.findById(2L)).thenReturn(Optional.of(newTeacher));

        EditSubjectRequest request = new EditSubjectRequest(newSubjectData,List.of(oldTeacherDto),List.of(newTeacherDto));
        subject.setTeachers(new HashSet<>(Collections.singletonList(teacher)));

        when(subjectRepository.findById(99L)).thenReturn(Optional.of(subject));
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        ResponseEntity<String> response = subjectService.editSubjectData(request);

        assertEquals("Data for the subject has been updated successfully.", response.getBody());
        assertEquals(subject.getTeachers().size(),1);
        assertTrue(subject.getTeachers().contains(newTeacher));
        verify(subjectRepository).save(subject);
    }

    @Test
    void shouldThrowExceptionWhenEditingNonExistentSubject() {
        EditSubjectRequest request = new EditSubjectRequest(subject,new ArrayList<>(),new ArrayList<>());

        when(subjectRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> subjectService.editSubjectData(request));
        assertEquals("Couldn't find subject with ID: 99", exception.getMessage());
    }

    @Test
    void shouldDeleteSubjectSuccessfully() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        ResponseEntity<String> response = subjectService.deleteSubject(1L);

        assertEquals("Deleted Mathematics correctly!", response.getBody());
        verify(subjectRepository).delete(subject);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentSubject() {
        when(subjectRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> subjectService.deleteSubject(99L));
        assertEquals("Couldn't find subject with ID: 99", exception.getMessage());
    }
}
