package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.ClassroomDto;
import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.dto.requests.EditClassRequest;
import com.troja.GradeBook.entity.Classroom;
import com.troja.GradeBook.entity.Role;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.mapper.ClassroomMapper;

import com.troja.GradeBook.mapper.TeacherMapper;
import com.troja.GradeBook.mapper.UserMapper;
import com.troja.GradeBook.repository.ClassroomRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import com.troja.GradeBook.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@AllArgsConstructor
@Service
public class ClassroomService {

    private ClassroomRepository classRepository;
    private ClassroomMapper classroomMapper;
    private TeacherRepository teacherRepository;
    private TeacherMapper teacherMapper;
    private UserRepository userRepository;
    private UserMapper userMapper;

    public ResponseEntity<?> findAllClasses() {
        List<Classroom> classes = classRepository.findAll();

        if (classes.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("There are no classes available. You can add them.");
        }

        List<ClassroomDto> classesDto = classes.stream()
                .map(classroom -> {
                    ClassroomDto classroomDto = classroomMapper.toDto(classroom);
                    User teacher = classroom.getMembersOfClass().stream()
                            .filter(user -> user.getRole().equals(Role.TEACHER))
                            .findFirst()
                            .orElse(null);
                    if (teacher != null) {
                        TeacherDto teacherDto = new TeacherDto(
                                teacher.getId(),
                                teacher.getEmail(),
                                teacher.getFirstName(),
                                teacher.getLastName());
                        classroomDto.setTeacherDto(teacherDto);
                    }
                    return classroomDto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(classesDto);
    }


    public ResponseEntity<?> addClass(String className, TeacherDto teacherDto) {

        if (classRepository.existsByName(className)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Subject with this name exists!");
        }
        Classroom classroom = new Classroom();
        classroom.setName(className);
        if (teacherDto != null) {
            Teacher teacher = teacherRepository.findById(teacherDto.getId())
                    .orElseThrow(() -> new RuntimeException("Couldn't find teacher!"));
            teacher.getUser().setClassroom(classroom);
        }
        classRepository.save(classroom);
        return ResponseEntity.ok("Added class correctly!");

    }

    public ResponseEntity<?> findStudentsOfClass(Long subjectId) {
        Classroom classroom = classRepository
                .findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Couldn't find class!"));

        Set<UserDto> students = classroom.getMembersOfClass().stream()
                .filter(user -> !user.getRole().equals(Role.TEACHER))
                .map(userMapper::toDto)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(students);
    }

    public ResponseEntity<?> deleteClass(Long classId) {
        try {
            classRepository.deleteById(classId);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return ResponseEntity.ok("Deleted class correctly!");
    }

    public ResponseEntity<?> editClassroom(EditClassRequest editClassRequest) {
        ClassroomDto classroomDto = editClassRequest.getClassroomDto();

        Classroom existingClassroom = classRepository.findById(classroomDto.getId())
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        if (!classroomDto.getName().equals(existingClassroom.getName())){
            if (!classRepository.existsByName(classroomDto.getName())) {
                existingClassroom.setName(classroomDto.getName());
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This class already exists!");
            }
        }

        List<User> existingClassMembers = new ArrayList<>(existingClassroom.getMembersOfClass());

        List<User> newClassMembers = editClassRequest.getStudents()
                .stream()
                .map(userMapper::toEntity)
                .collect(Collectors.toList());

        for (User user : newClassMembers) {

            User newUser = userRepository.findById(user.getId()).orElseThrow();
            if(existingClassMembers.contains(newUser)){
                existingClassMembers.remove(newUser);
            } else {
                newUser.setClassroom(existingClassroom);
                userRepository.save(newUser);
            }
        }

        for (User user : existingClassMembers){
            user.setClassroom(null);
            userRepository.save(user);
        }

        User existingTeacher = existingClassMembers.stream()
                .filter(user -> user.getRole().equals(Role.TEACHER))
                .findFirst()
                .orElse(null);

        if (existingTeacher != null && !existingTeacher.getEmail().equals(classroomDto.getTeacherDto().getEmail())) {
            existingTeacher.setClassroom(null);
            userRepository.save(existingTeacher);
            existingClassMembers.remove(existingTeacher);
        }

        User newTeacher = userRepository.findByEmail(classroomDto.getTeacherDto().getEmail())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        newTeacher.setClassroom(existingClassroom);

        if (!newClassMembers.contains(newTeacher)) {
            newClassMembers.add(newTeacher);
        }

        existingClassroom.setMembersOfClass(newClassMembers);

        classRepository.save(existingClassroom);

        return ResponseEntity.ok("Updated data correctly!");
    }

}

