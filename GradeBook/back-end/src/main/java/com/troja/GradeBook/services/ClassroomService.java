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

import com.troja.GradeBook.mapper.TeacherMapper;
import com.troja.GradeBook.mapper.UserMapper;
import com.troja.GradeBook.repository.ClassroomRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import com.troja.GradeBook.repository.UserRepository;
import com.troja.GradeBook.services.IServices.IClassroomService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@AllArgsConstructor
@Service
public class ClassroomService implements IClassroomService {

    private ClassroomRepository classRepository;
    private ClassroomMapper classroomMapper;
    private TeacherRepository teacherRepository;
    private UserRepository userRepository;
    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(ClassroomService.class);

    @Override
    public ResponseEntity<?> findAllClasses() {
        List<Classroom> classes = classRepository.findAll();

        if (classes.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("There are no classes available. You can add them.");
        }

        List<ClassroomDto> classesDto = classes.stream()
                .map(this::mapClassroomWithTeacher)
                .collect(Collectors.toList());

        return ResponseEntity.ok(classesDto);
    }

    @Override
    public ResponseEntity<?> addClass(String className, TeacherDto teacherDto) {
        if (classRepository.existsByName(className)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Class with this name exists!");
        }

        Classroom classroom = new Classroom();
        classroom.setName(className);

        if (teacherDto != null) {
            Optional<Teacher> teacher = teacherRepository.findById(teacherDto.getId());
            teacher.get().getUser().setClassroom(classroom);
        }

        classRepository.save(classroom);
        return ResponseEntity.ok("Added class correctly!");
    }

    @Override
    public ResponseEntity<?> findStudentsOfClass(Long classId) {
        Optional<Classroom> classroom = classRepository.findById(classId);
        if(classroom.isEmpty()){
            throw new ResourceNotFoundException("Couldn't find class!");
        }

        List<UserDto> students = classroom.get().getMembersOfClass().stream()
                .filter(user -> !user.getRole().equals(Role.TEACHER))
                .map(userMapper::toDto)
                .sorted(Comparator.comparing(UserDto::getFirstName)
                        .thenComparing(UserDto::getLastName))
                .collect(Collectors.toList());

        return ResponseEntity.ok(students);
    }

    @Override
    public ResponseEntity<?> deleteClass(Long classId) {
        try {
            classRepository.deleteById(classId);
            return ResponseEntity.ok("Deleted class correctly!");
        } catch (Exception ex) {
            logger.error("Error while deleting class", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while deleting class.");
        }
    }

    @Override
    public ResponseEntity<?> editClassroom(EditClassRequest editClassRequest) {
        ClassroomDto classroomDto = editClassRequest.getClassroomDto();
        Classroom existingClassroom = classRepository
                .findById(classroomDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find class!"));

        updateClassNameIfNeeded(existingClassroom, classroomDto);
        updateTeacherIfNeeded(existingClassroom, classroomDto);
        updateStudents(existingClassroom, editClassRequest.getStudents());

        classRepository.save(existingClassroom);

        return ResponseEntity.ok("Updated data correctly!");
    }

    private ClassroomDto mapClassroomWithTeacher(Classroom classroom) {
        ClassroomDto classroomDto = classroomMapper.toDto(classroom);
        TeacherDto teacherDto = mapTeacherFromClassroom(classroom);
        classroomDto.setTeacherDto(teacherDto);
        return classroomDto;
    }

    private TeacherDto mapTeacherFromClassroom(Classroom classroom) {
        User teacher = classroom.getMembersOfClass().stream()
                .filter(user -> user.getRole().equals(Role.TEACHER))
                .findFirst()
                .orElse(null);

        if (teacher != null) {
            return new TeacherDto(
                    teacher.getId(),
                    teacher.getEmail(),
                    teacher.getFirstName(),
                    teacher.getLastName()
            );
        }
        return null;
    }

    private void updateClassNameIfNeeded(Classroom existingClassroom, ClassroomDto classroomDto) {
        if (!classroomDto.getName().equals(existingClassroom.getName())) {
            if (!classRepository.existsByName(classroomDto.getName())) {
                existingClassroom.setName(classroomDto.getName());
            } else {
                throw new RuntimeException("This class already exists!");
            }
        }
    }

    private void updateTeacherIfNeeded(Classroom existingClassroom, ClassroomDto classroomDto) {
        User currentTeacher = existingClassroom.getMembersOfClass().stream()
                .filter(user -> user.getRole().equals(Role.TEACHER))
                .findFirst()
                .orElse(null);

        if (currentTeacher != null && !currentTeacher.getEmail().equals(classroomDto.getTeacherDto().getEmail())) {
            currentTeacher.setClassroom(null);
            userRepository.save(currentTeacher);
            existingClassroom.getMembersOfClass().remove(currentTeacher);
        }

        User newTeacher = userRepository.findByEmail(classroomDto.getTeacherDto().getEmail())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        newTeacher.setClassroom(existingClassroom);
        existingClassroom.getMembersOfClass().add(newTeacher);
    }

    private void updateStudents(Classroom existingClassroom, List<UserDto> studentsDto) {
        List<User> currentStudents = new ArrayList<>(existingClassroom.getMembersOfClass());
        List<User> newStudents = studentsDto.stream()
                .map(userMapper::toEntity)
                .collect(Collectors.toList());

        for (User newUser : newStudents) {
            User existingUser = userRepository.findById(newUser.getId())
                    .orElseThrow(() -> new RuntimeException("Student not found: " + newUser.getId()));

            existingUser.setClassroom(existingClassroom);
            userRepository.save(existingUser);

            currentStudents.remove(existingUser);
        }

        for (User userToRemove : currentStudents) {
            if(userToRemove.getRole()!=Role.TEACHER) {
                userToRemove.setClassroom(null);
                userRepository.save(userToRemove);
            }
        }

        existingClassroom.setMembersOfClass(newStudents);
    }

}



