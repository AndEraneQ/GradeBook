package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.ClassroomDto;
import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.dto.requests.EditClassRequest;
import com.troja.GradeBook.entity.Classroom;
import com.troja.GradeBook.entity.Role;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.exception.resource.ResourceNotFoundException;
import com.troja.GradeBook.mapper.ClassroomMapper;
import com.troja.GradeBook.mapper.UserMapper;
import com.troja.GradeBook.repository.ClassroomRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import com.troja.GradeBook.repository.UserRepository;
import com.troja.GradeBook.services.ClassroomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.C;

import java.util.*;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClassroomServiceTest {

    @Mock
    private ClassroomRepository classroomRepository;

    @Mock
    private ClassroomMapper classroomMapper;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private ClassroomService classroomService;

    @Mock
    private UserRepository userRepository;

    private Classroom existingClassroom;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        existingClassroom = new Classroom();
        existingClassroom.setId(1L);
        existingClassroom.setName("Math 101");
        existingClassroom.setMembersOfClass(new ArrayList<>());
    }

    @Test
    public void testFindAllClasses_NoClasses() {
        // Arrange
        when(classroomRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = classroomService.findAllClasses();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("There are no classes available. You can add them.", response.getBody());
        verify(classroomRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllClasses_WithClassesAndNoTeacher() {
        // Arrange

        Classroom classroom1 = new Classroom();
        classroom1.setId(1L);
        classroom1.setName("Math");
        classroom1.setMembersOfClass(Collections.emptyList());

        Classroom classroom2 = new Classroom();
        classroom2.setId(2L);
        classroom2.setName("Science");
        classroom2.setMembersOfClass(Collections.emptyList());

        List<Classroom> classrooms = Arrays.asList(classroom1, classroom2);

        ClassroomDto classroomDto1 = new ClassroomDto();
        classroomDto1.setId(1L);
        classroomDto1.setName("Math");

        ClassroomDto classroomDto2 = new ClassroomDto();
        classroomDto2.setId(2L);
        classroomDto2.setName("Science");

        when(classroomRepository.findAll()).thenReturn(classrooms);
        when(classroomMapper.toDto(classroom1)).thenReturn(classroomDto1);
        when(classroomMapper.toDto(classroom2)).thenReturn(classroomDto2);

        // Act
        ResponseEntity<?> response = classroomService.findAllClasses();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ClassroomDto> result = (List<ClassroomDto>) response.getBody();
        assertEquals(2, result.size());
        assertEquals(classroomDto1, result.get(0));
        assertEquals(classroomDto2, result.get(1));

        verify(classroomRepository, times(1)).findAll();
        verify(classroomMapper, times(1)).toDto(classroom1);
        verify(classroomMapper, times(1)).toDto(classroom2);
    }

    @Test
    public void testFindAllClasses_WithTeacher() {
        // Arrange
        User teacher = new User();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        teacher.setEmail("john.doe@example.com");
        teacher.setRole(Role.TEACHER);

        Classroom classroom = new Classroom();
        classroom.setId(1L);
        classroom.setName("Math");
        classroom.setMembersOfClass(Collections.singletonList(teacher));

        ClassroomDto classroomDto = new ClassroomDto();
        classroomDto.setId(1L);
        classroomDto.setName("Math");

        TeacherDto teacherDto = new TeacherDto(1L, "john.doe@example.com", "John", "Doe");
        classroomDto.setTeacherDto(teacherDto);

        when(classroomRepository.findAll()).thenReturn(Collections.singletonList(classroom));
        when(classroomMapper.toDto(classroom)).thenReturn(classroomDto);

        // Act
        ResponseEntity<?> response = classroomService.findAllClasses();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ClassroomDto> result = (List<ClassroomDto>) response.getBody();
        assertEquals(1, result.size());
        assertEquals(classroomDto, result.get(0));

        verify(classroomRepository, times(1)).findAll();
        verify(classroomMapper, times(1)).toDto(classroom);
    }

    @Test
    public void testAddClass_ClassExists() {
        String className = "Math";
        when(classroomRepository.existsByName(className)).thenReturn(true);

        ResponseEntity<?> response = classroomService.addClass(className, null);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Class with this name exists!", response.getBody());
        verify(classroomRepository, times(1)).existsByName(className);
        verify(classroomRepository, never()).save(any(Classroom.class));
    }

    @Test
    public void testAddClass_NoTeacher() {
        String className = "Math";
        when(classroomRepository.existsByName(className)).thenReturn(false);

        ResponseEntity<?> response = classroomService.addClass(className, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Added class correctly!", response.getBody());
        verify(classroomRepository, times(1)).existsByName(className);
        verify(classroomRepository, times(1)).save(any(Classroom.class));
    }

    @Test
    public void testAddClass_WithTeacher() {
        String className = "Math";
        TeacherDto teacherDto = new TeacherDto(1L, "john.doe@example.com", "John", "Doe");

        User teacherUser = new User();
        Teacher teacher = new Teacher();
        teacher.setUser(teacherUser);

        when(classroomRepository.existsByName(className)).thenReturn(false);
        when(teacherRepository.findById(teacherDto.getId())).thenReturn(Optional.of(teacher));

        ResponseEntity<?> response = classroomService.addClass(className, teacherDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Added class correctly!", response.getBody());
        verify(classroomRepository, times(1)).existsByName(className);
        verify(teacherRepository, times(1)).findById(teacherDto.getId());
        verify(classroomRepository, times(1)).save(any(Classroom.class));
        assertEquals(className, teacherUser.getClassroom().getName());
    }

    @Test
    public void testFindStudentsOfClass_ClassNotFound() {
        Long classId = 1L;
        when(classroomRepository.findById(classId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> classroomService.findStudentsOfClass(classId),
                "Expected exception not thrown"
        );
        assertEquals("Couldn't find class!", exception.getMessage());
        verify(classroomRepository, times(1)).findById(classId);
    }

    @Test
    public void testFindStudentsOfClass_NoStudents() {
        Long classId = 1L;
        Classroom classroom = new Classroom();
        classroom.setMembersOfClass(new ArrayList<>());

        when(classroomRepository.findById(classId)).thenReturn(Optional.of(classroom));

        ResponseEntity<?> response = classroomService.findStudentsOfClass(classId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
        verify(classroomRepository, times(1)).findById(classId);
    }

    @Test
    public void testFindStudentsOfClass_WithStudents() {
        Long classId = 1L;
        Classroom classroom = new Classroom();

        User student1 = new User();
        student1.setId(1L);
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setRole(Role.STUDENT);

        User student2 = new User();
        student2.setId(2L);
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setRole(Role.STUDENT);

        User teacher = new User();
        teacher.setId(3L);
        teacher.setFirstName("Teacher");
        teacher.setLastName("LastName");
        teacher.setRole(Role.TEACHER);

        List<User> membersOfClass = new ArrayList<>(Arrays.asList(student1, student2, teacher));
        classroom.setMembersOfClass(membersOfClass);

        UserDto student1Dto = new UserDto(
                1L,
                "johnDoe@gmail.com",
                "John",
                "Doe",
                "2a",
                Role.STUDENT
        );
        UserDto student2Dto = new UserDto(
                1L,
                "Jane@gmail.com",
                "Jane",
                "Smith",
                "2a",
                Role.STUDENT
        );

        when(classroomRepository.findById(classId)).thenReturn(Optional.of(classroom));
        when(userMapper.toDto(student1)).thenReturn(student1Dto);
        when(userMapper.toDto(student2)).thenReturn(student2Dto);

        ResponseEntity<?> response = classroomService.findStudentsOfClass(classId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<UserDto> expectedSortedStudents = Arrays.asList(student2Dto, student1Dto);
        assertEquals(expectedSortedStudents, response.getBody());

        verify(classroomRepository, times(1)).findById(classId);
        verify(userMapper, times(1)).toDto(student1);
        verify(userMapper, times(1)).toDto(student2);
        verify(userMapper, never()).toDto(teacher);
    }

    @Test
    void testDeleteClass_Success() {
        Long classId = 1L;

        ResponseEntity<?> response = classroomService.deleteClass(classId);

        verify(classroomRepository).deleteById(classId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted class correctly!", response.getBody());
    }

    @Test
    void testDeleteClass_Error() {
        Long classId = 1L;

        doThrow(new RuntimeException("Database error")).when(classroomRepository).deleteById(classId);

        ResponseEntity<?> response = classroomService.deleteClass(classId);

        verify(classroomRepository).deleteById(classId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error while deleting class.", response.getBody());
    }

    @Test
    void editClassroom_ShouldUpdateClassName_WhenNameIsDifferent() {
        ClassroomDto classroomDto = new ClassroomDto();
        classroomDto.setId(1L);
        classroomDto.setName("Math 102");
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setEmail("teacherDto@gmail.com");
        classroomDto.setTeacherDto(teacherDto);
        EditClassRequest editClassRequest = new EditClassRequest(classroomDto, new ArrayList<>());

        when(userRepository.findByEmail("teacherDto@gmail.com")).thenReturn(Optional.of(new User()));
        when(classroomRepository.findById(1L)).thenReturn(Optional.of(existingClassroom));
        when(classroomRepository.existsByName("Math 102")).thenReturn(false);

        ResponseEntity<?> response = classroomService.editClassroom(editClassRequest);

        assertEquals("Updated data correctly!", response.getBody());
        assertEquals("Math 102", existingClassroom.getName());
        verify(classroomRepository).save(existingClassroom);
    }

    @Test
    void editClassroom_ShouldThrowException_WhenClassNotFound() {
        ClassroomDto classroomDto = new ClassroomDto();
        classroomDto.setId(1L);
        EditClassRequest editClassRequest = new EditClassRequest(classroomDto, new ArrayList<>());

        when(classroomRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            classroomService.editClassroom(editClassRequest);
        });

        assertEquals("Couldn't find class!", exception.getMessage());
    }

    @Test
    void editClassroom_ShouldThrowException_WhenClassNameAlreadyExists() {
        ClassroomDto classroomDto = new ClassroomDto();
        classroomDto.setId(1L);
        classroomDto.setName("Math 102");
        EditClassRequest editClassRequest = new EditClassRequest(classroomDto, new ArrayList<>());

        when(classroomRepository.findById(1L)).thenReturn(Optional.of(existingClassroom));
        when(classroomRepository.existsByName("Math 102")).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            classroomService.editClassroom(editClassRequest);
        });

        assertEquals("This class already exists!", exception.getMessage());
    }

    @Test
    void editClassroom_ShouldUpdateTeacher_WhenDifferentTeacherProvided() {
        ClassroomDto classroomDto = new ClassroomDto();
        classroomDto.setId(1L);
        classroomDto.setName("1a");
        classroomDto.setTeacherDto(new TeacherDto(
                1L,
                "new_teacher@example.com",
                "John",
                "Doe")
        );

        User currentTeacher = new User();
        currentTeacher.setEmail("old_teacher@example.com");
        currentTeacher.setRole(Role.TEACHER);
        existingClassroom.getMembersOfClass().add(currentTeacher);

        User newTeacher = new User();
        newTeacher.setEmail("new_teacher@example.com");
        newTeacher.setRole(Role.TEACHER);

        EditClassRequest editClassRequest = new EditClassRequest(classroomDto, new ArrayList<>());

        when(classroomRepository.findById(1L)).thenReturn(Optional.of(existingClassroom));
        when(userRepository.findByEmail("new_teacher@example.com")).thenReturn(Optional.of(newTeacher));

        ResponseEntity<?> response = classroomService.editClassroom(editClassRequest);

        assertEquals("Updated data correctly!", response.getBody(), "Response body should indicate success");
        assertNull(currentTeacher.getClassroom());
        assertEquals(existingClassroom, newTeacher.getClassroom(), "New teacher should be associated with the updated classroom");
    }


    @Test
    void editClassroom_ShouldRemoveOldStudentsAndAddNew() {
        ClassroomDto classroomDto = new ClassroomDto();
        classroomDto.setId(1L);
        classroomDto.setName("1a");
        classroomDto.setTeacherDto(new TeacherDto(
                1L,
                "teacher@example.com",
                "John",
                "Doe")
        );
        UserDto newStudentDto = new UserDto();
        newStudentDto.setId(2L);

        EditClassRequest editClassRequest = new EditClassRequest(classroomDto, Arrays.asList(newStudentDto));

        User existingStudent = new User();
        existingStudent.setId(3L);
        existingStudent.setRole(Role.STUDENT);
        existingClassroom.getMembersOfClass().add(existingStudent);

        when(userRepository.findByEmail("teacher@example.com")).thenReturn(Optional.of(new User()));
        when(classroomRepository.findById(1L)).thenReturn(Optional.of(existingClassroom));
        when(userRepository.findById(3L)).thenReturn(Optional.of(existingStudent));
        when(userMapper.toEntity(newStudentDto)).thenReturn(existingStudent);

        ResponseEntity<?> response = classroomService.editClassroom(editClassRequest);

        assertEquals("Updated data correctly!", response.getBody());
        assertEquals(1, existingClassroom.getMembersOfClass().size());
        assertEquals(existingClassroom, existingStudent.getClassroom());
        verify(userRepository).save(existingStudent);
    }
}




