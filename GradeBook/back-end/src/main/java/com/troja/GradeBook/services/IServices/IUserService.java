package com.troja.GradeBook.services.IServices;

import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.dto.requests.EditUserDataRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<UserDto> getCurrentUser(Long id);
    ResponseEntity<List<UserDto>> getAllUsers();
    ResponseEntity<?> deleteUser(Long id);
    ResponseEntity<?> editUserData(EditUserDataRequest editUserDataRequest);
    ResponseEntity<?> getStudents();
}
