package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.ClassroomDto;
import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.entity.Classroom;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.mapper.ClassroomMapper;

import com.troja.GradeBook.mapper.UserMapper;
import com.troja.GradeBook.repository.ClassroomRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@AllArgsConstructor
@Service
public class ClassroomService {

    private ClassroomRepository classRepository;
    private ClassroomMapper classroomMapper;
    private TeacherRepository teacherRepository;
    private UserMapper userMapper;

    public ResponseEntity<?> findAllClasses() {
        List<ClassroomDto> classes = classRepository.findAll()
                .stream()
                .map(classroomMapper::toDto)
                .sorted(Comparator.comparing(ClassroomDto::getName))
                .collect(Collectors.toList());

        if(classes.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("There are no classes available. You can add them.");
        }
        return ResponseEntity.ok(classes);
    }

    public ResponseEntity<?> addClass(String className, TeacherDto teacherDto) {
        Classroom classroom = new Classroom();
        classroom.setName(className);

        if(teacherDto!=null) {
            Teacher teacher = new Teacher();
            try {
                teacher = teacherRepository.
                        findById(teacherDto.getId())
                        .orElseThrow(() -> new RuntimeException("Couldn't set teacher"));
            } catch (RuntimeException ex) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ex.getMessage());
            }
            classroom.setTeacher(teacher);
        }

        classRepository.save(classroom);
        return ResponseEntity.ok("Added class correctly!");

    }

    public ResponseEntity<?> findStudentsOfClass(Long subjectId) {
        Classroom classroom = classRepository
                .findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Couldn't find class!"));

        return ResponseEntity.ok(classroom
                .getStudents()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toSet())
        );
    }
}

