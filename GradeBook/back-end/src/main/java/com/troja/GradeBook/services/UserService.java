package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.RoleDto;
import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.entity.RoleEnum;
import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.exception.MyCustomException;
import com.troja.GradeBook.mapper.UserMapper;
import com.troja.GradeBook.repository.SubjectRepository;
import com.troja.GradeBook.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SubjectRepository subjectRepository;

    public ResponseEntity<UserDto> getCurrentUser(Long id){
        User currentUser = userRepository.findById(id)
                .orElseThrow(() -> new MyCustomException("error","couldn't find user"));
        return ResponseEntity.ok(userMapper.toDto(currentUser));
    }

    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll().stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList()));
    }

    public ResponseEntity<?> getAllUsersByRole(RoleDto roleDto){
        RoleEnum roleEnum;
        try {
            roleEnum = RoleEnum.valueOf(roleDto.getName().toUpperCase());
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid role: " + roleDto.getName());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        List<User> allUsersByRole = userRepository.findByRole_Role(roleEnum);
        return ResponseEntity.ok(allUsersByRole.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList()));
    }

    public ResponseEntity<List<UserDto>> getAllTeachersBySubject(String name) {
        // TODO: FIND SUBJECT OBJECT
        Subject subject = subjectRepository.findByName(name);
        // TODO: TAKE ALL TEACHERS
        List<User> teachers = userRepository.findBySubjects_Id(subject.getId());
        // TODO: IF LIST IS EMPTY RETURN CODE 204
        if (teachers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(
                teachers.stream()
                        .map(userMapper::toDto)
                        .collect(Collectors.toList()));
    }
}
