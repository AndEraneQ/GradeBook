package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.dto.requests.EditUserDataRequest;
import com.troja.GradeBook.entity.Residence;
import com.troja.GradeBook.entity.Role;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.exception.user.UserNotFoundException;
import com.troja.GradeBook.mapper.UserMapper;
import com.troja.GradeBook.repository.UserRepository;
import com.troja.GradeBook.services.IServices.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<UserDto> getCurrentUser(Long id) {
        User currentUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return ResponseEntity.ok(userMapper.toDto(currentUser));
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @Override
    public ResponseEntity<?> editUserData(EditUserDataRequest editUserDataRequest) {
        if (!editUserDataRequest.getPassword().equals(editUserDataRequest.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords need to be the same!");
        }

        User user = userRepository.findById(editUserDataRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!editUserDataRequest.getPassword().isEmpty()) {
            String newPassword = passwordEncoder.encode(editUserDataRequest.getPassword());
            user.setPassword(newPassword);
        }

        Residence residence = user.getResidence();
        if (residence != null) {
            residence.setApartmentNumber(editUserDataRequest.getApartmentNumber());
            residence.setCity(editUserDataRequest.getCity());
            residence.setBuildingNumber(editUserDataRequest.getBuildingNumber());
            residence.setStreet(editUserDataRequest.getStreet());
            user.setResidence(residence);
        }

        user.setFirstName(editUserDataRequest.getFirstName());
        user.setLastName(editUserDataRequest.getLastName());

        userRepository.save(user);

        return ResponseEntity.ok("Updated data successfully.");
    }

    @Override
    public ResponseEntity<?> getStudents() {
        List<UserDto> students = userRepository.findByRole(Role.STUDENT)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(students);
    }
}
