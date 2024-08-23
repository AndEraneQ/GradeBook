package com.troja.GradeBook.repository;

import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


class TeacherRepositoryTest extends BaseTest{

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    private Teacher teacher;
    private User user;

    @BeforeEach
    void setData() {
        user = new User();
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        user = userRepository.save(user);

        teacher = new Teacher();
        teacher.setUser(user);

        teacherRepository.save(teacher);
    }

    @AfterEach
    void removeData() {
        teacherRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testFindAll_ReturnsListOfTeachers() {
        // Act
        List<Teacher> teachers = teacherRepository.findAll();

        // Assert
        assertNotNull(teachers);
        assertFalse(teachers.isEmpty(), "The teacher list should not be empty");
        assertEquals(1, teachers.size(), "There should be one teacher in the list");
    }

    @Test
    void testFindAll_WhenNoTeachers_ReturnsEmptyList() {
        // Arrange
        teacherRepository.deleteAll();

        // Act
        List<Teacher> teachers = teacherRepository.findAll();

        // Assert
        assertTrue(teachers.isEmpty(), "The teacher list should be empty");
    }

    @Test
    public void testFindTeachersBySubjectId() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Matematyka");

        subjectRepository.save(subject);

        Set<Subject> subjects = new HashSet<>();
        subjects.add(subject);
        teacher.setSubjects(subjects);


        teacherRepository.save(teacher);
        //when
        List<Teacher> teachers = teacherRepository.findTeachersBySubjectId(1L);

        // then
        assertEquals(1, teachers.size());
        assertEquals("test@example.com",teachers.get(0).getUser().getEmail());
    }

    @Test
    public void testFindTeachersBySubjectId_NoTeachersFound() {
        // when
        List<Teacher> teachers = teacherRepository.findTeachersBySubjectId(200L);

        // then
        assertEquals(0, teachers.size());
    }
}
