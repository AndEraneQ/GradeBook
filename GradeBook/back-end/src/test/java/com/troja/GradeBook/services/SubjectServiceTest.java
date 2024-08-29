package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.SubjectDto;
import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.dto.requests.AddSubjectRequest;
import com.troja.GradeBook.dto.requests.EditSubjectRequest;
import com.troja.GradeBook.entity.Classroom;
import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.mapper.SubjectMapper;
import com.troja.GradeBook.mapper.TeacherMapper;
import com.troja.GradeBook.repository.SubjectRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testcontainers.shaded.org.bouncycastle.cert.DeltaCertificateTool.subject;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {

    @InjectMocks
    private SubjectService underTest;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private SubjectMapper subjectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnListOfSubjectsDtoWhenGetAllSubjects(){
        //given
        Subject subject1 = new Subject("Mathematic");
        Subject subject2 = new Subject("Phisical Education");
        Subject subject3 = new Subject("Biology");
        when(subjectRepository.findAll()).thenReturn(List.of(subject1,subject2,subject3));

        //when
        List<SubjectDto> subjectDtos;
        subjectDtos = underTest.getAllSubject();
        //then
        assertEquals(3,subjectDtos.size());
        verify(subjectRepository).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenGetAllSubjects(){
        //given
        when(subjectRepository.findAll()).thenReturn(List.of());
        //when
        List<SubjectDto> subjectDtos;
        subjectDtos = underTest.getAllSubject();
        //then
        assertTrue(subjectDtos.isEmpty());
        verify(subjectRepository).findAll();
    }


    @Test
    public void testAddSubject_Success() {
        // given
        Teacher teacher = new Teacher(1L, new User(), new HashSet<>(), new Classroom());

        TeacherMapper teacherMapper = new TeacherMapper();
        TeacherDto teacherDto = teacherMapper.toDto(teacher);

        AddSubjectRequest request = new AddSubjectRequest();
        request.setName("Mathematic");
        request.setListOfTeachers(Arrays.asList(teacherDto));
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(subjectRepository.existsByName("Mathematic")).thenReturn(false);
        when(subjectRepository.save(any(Subject.class))).thenReturn(new Subject("Mathematic"));
        when(subjectRepository.findByName("Mathematic")).thenReturn(Optional.of(new Subject("Mathematic")));

        // when
        ResponseEntity<?> response = underTest.addSubject(request);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Added subject correctly", response.getBody());
        verify(subjectRepository, times(2)).save(any(Subject.class));
    }

    @Test
    public void testAddSubject_EmptyName() {
        // given
        AddSubjectRequest request = new AddSubjectRequest();
        request.setName(""); //

        // when
        ResponseEntity<?> response = underTest.addSubject(request);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Subject name cannot be empty.", response.getBody());
    }

    @Test
    public void testAddSubject_Conflict() {
        //given
        AddSubjectRequest request = new AddSubjectRequest();
        request.setName("Mathematic");

        when(subjectRepository.existsByName("Mathematic")).thenReturn(true);

        // when
        ResponseEntity<?> response = underTest.addSubject(request);

        // then
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Subject with name 'Mathematic' already exists.", response.getBody());
    }

    @Test
    public void testAddSubject_ServerError() {
        //given
        AddSubjectRequest request = new AddSubjectRequest();
        request.setName("Mathematic");
        when(subjectRepository.existsByName("Mathematic")).thenReturn(false);
        when(subjectRepository.save(any(Subject.class))).thenThrow(new RuntimeException("Save error"));

        // when
        ResponseEntity<?> response = underTest.addSubject(request);

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Server error, try again later: Save error"));
    }

    @Test
    public void testEditSubjectData_Success() {
        // given
        Subject existingSubject = new Subject(1L, "Math", new HashSet<>());
        Teacher teacher1 = new Teacher(1L, new User(), new HashSet<>(),new Classroom());
        Teacher teacher2 = new Teacher(2L, new User(), new HashSet<>(),new Classroom());

        TeacherMapper teacherMapper = new TeacherMapper();
        List<TeacherDto> teachersToAdd = Arrays.asList(teacherMapper.toDto(teacher1));
        List<TeacherDto> teachersToDelete = Arrays.asList(teacherMapper.toDto(teacher2));

        existingSubject.getTeachers().add(teacher2);

        Subject newSubject = new Subject(1L, "Mathematic", new HashSet<>());

        EditSubjectRequest request = new EditSubjectRequest(
                newSubject,
                teachersToAdd,
                teachersToDelete
        );


        when(subjectRepository.findById(1L)).thenReturn(Optional.of(existingSubject));
        when(teacherRepository.findById(teacher1.getId())).thenReturn(Optional.of(teacher1));
        when(teacherRepository.findById(teacher2.getId())).thenReturn(Optional.of(teacher2));

        // when
        ResponseEntity<?> response = underTest.editSubjectData(request);

        // then
        assertEquals("Data for the subject has been updated successfully.", response.getBody());
        assertEquals("Mathematic", existingSubject.getName());
        assertTrue(existingSubject.getTeachers().contains(teacher2));
        assertFalse(existingSubject.getTeachers().contains(teacher1));

        verify(subjectRepository).save(existingSubject);
    }

    @Test
    public void testEditSubjectData_SubjectNotFound() {
        // given
        EditSubjectRequest request = new EditSubjectRequest(new Subject(
                1L,
                "Math",
                new HashSet<>()),
                List.of(),
                List.of()
        );

        // when
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        // then
        assertThrows(NoSuchElementException.class, () -> underTest.editSubjectData(request));
    }

    @Test
    public void testEditSubjectData_TeacherNotFound() {
        // given
        Subject existingSubject = new Subject(1L, "Math", new HashSet<>());

        TeacherDto teacherToAdd = new TeacherDto(2L,"test@gmail.com","Joe","Smith");

        EditSubjectRequest request = new EditSubjectRequest(
                new Subject(1L, "Math", new HashSet<>()),
                Arrays.asList(teacherToAdd),
                List.of()
        );

        // when
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(existingSubject));
        when(teacherRepository.findById(teacherToAdd.getId())).thenReturn(Optional.empty());

        // then
        assertThrows(NoSuchElementException.class, () -> underTest.editSubjectData(request));
    }
    @Test
    void testDeleteSubject_SubjectNotFound(){
        //given
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        Exception exception = assertThrows(RuntimeException.class,
                () -> {underTest.deleteSubject(1L);
        });
        //then
        assertThat(exception.getMessage()).isEqualTo("Couldn't find subject");
    }

    @Test
    void testDeleteSubject_DeletedCorrectlyWithoutTeachers() {
        // given
        Subject subject = new Subject(1L,"Math",new HashSet<>());
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        // when
        ResponseEntity<?> response = underTest.deleteSubject(1L);

        // then
        verify(subjectRepository, times(1)).delete(subject);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("Deleted " + subject.getName() + " correctly!");
    }

    @Test
    void testDeleteSubject_DeletedCorrectlyWithTeachers() {
        // given
        Subject subject = new Subject(1L,"Math",new HashSet<>());
        Teacher teacher1 = new Teacher(1L, new User(), new HashSet<>(),new Classroom());
        teacher1.getSubjects().add(subject);
        subject.getTeachers().add(teacher1);
        Teacher teacher2 = new Teacher(2L, new User(), new HashSet<>(),new Classroom());

        teacher2.getSubjects().add(subject);
        subject.getTeachers().add(teacher2);

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        // when
        ResponseEntity<?> response = underTest.deleteSubject(1L);

        // then
        verify(subjectRepository, times(1)).delete(subject);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("Deleted " + subject.getName() + " correctly!");

        for (Teacher teacher : subject.getTeachers()) {
            assertThat(teacher.getSubjects()).doesNotContain(subject);
        }
    }
}


