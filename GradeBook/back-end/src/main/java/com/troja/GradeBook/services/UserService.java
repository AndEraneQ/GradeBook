package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.exception.MyCustomException;
import com.troja.GradeBook.mapper.UserMapper;
import com.troja.GradeBook.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<UserDto> getCurrentUser(Long id){
        User currentUser = userRepository.findById(id)
                .orElseThrow(() -> new MyCustomException("error","couldn't find user"));
        return ResponseEntity.ok(userMapper.toDto(currentUser));
    }
}
