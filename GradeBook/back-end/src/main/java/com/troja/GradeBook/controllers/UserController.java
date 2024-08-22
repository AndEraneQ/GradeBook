//package com.troja.GradeBook.controllers;
//
//import com.troja.GradeBook.dto.RoleDto;
//import com.troja.GradeBook.dto.UserDto;
//import com.troja.GradeBook.services.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//@AllArgsConstructor
//public class UserController {
//
//    private UserService userService;
//
//    @GetMapping("/user/{id}")
//    public ResponseEntity<UserDto> getCurrentUser(@PathVariable Long id) {
//        return userService.getCurrentUser(id);
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<List<UserDto>> getAllUsers(){
//        return userService.getAllUsers();
//    }
//
////    @PostMapping("/users/byRoles")
////    public ResponseEntity<List<UserDto>> getAllUsersByRole(@RequestBody RoleDto roleDto){
////        return (ResponseEntity<List<UserDto>>) userService.getAllUsersByRole(roleDto);
////    }
//
//    @GetMapping("/teacher/{subjectName}")
//    public ResponseEntity<List<UserDto>> getAllTeachersBySubject(@PathVariable String subjectName){
//        return userService.getAllTeachersBySubject(subjectName);
//    }
//}
