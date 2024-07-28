package com.troja.GradeBook.services;

import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public ResponseEntity<Optional<User>> getCurrentUser(Long id){
        Optional<User> currentUser = userRepository.findById(id);
        return ResponseEntity.ok(currentUser);
    }
}
