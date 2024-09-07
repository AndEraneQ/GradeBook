package com.troja.GradeBook.controllers;

import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.dto.requests.EditUserDataRequest;
import com.troja.GradeBook.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getCurrentUser(@PathVariable Long id) {
        return userService.getCurrentUser(id);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @PutMapping("/edit/user")
    public ResponseEntity<?> editUserData(@RequestBody @Valid EditUserDataRequest editUserDataRequest){
        return userService.editUserData(editUserDataRequest);
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(){
        return userService.getStudents();
    }
}
