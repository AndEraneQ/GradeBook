package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.dto.requests.EditUserDataRequest;
import com.troja.GradeBook.entity.Residence;
import com.troja.GradeBook.entity.Role;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.exception.MyCustomException;
import com.troja.GradeBook.mapper.UserMapper;
import com.troja.GradeBook.repository.ResidenceRepository;
import com.troja.GradeBook.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<UserDto> getCurrentUser(Long id){
        User currentUser = userRepository.findById(id)
                .orElseThrow(() -> new MyCustomException("error","couldn't find user"));
        return ResponseEntity.ok(userMapper.toDto(currentUser));
    }

    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList()));
    }

    public ResponseEntity<?> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    public ResponseEntity<?> editUserData(EditUserDataRequest editUserDataRequest) {

        if(!editUserDataRequest.getPassword().equals(editUserDataRequest.getConfirmPassword())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Passwords need to be the same!");
        }

        User user = new User();
        if(!editUserDataRequest.getPassword().isEmpty()){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String newPassword = passwordEncoder.encode(editUserDataRequest.getPassword());
            user.setPassword(newPassword);
        }

        try{
        user = userRepository.findById(editUserDataRequest.getUserId())
                .orElseThrow();
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Couldn't edit user data.");
        }

        Residence residence = user.getResidence();
        residence.setApartmentNumber(editUserDataRequest.getApartmentNumber());
        residence.setCity(editUserDataRequest.getCity());
        residence.setBuildingNumber(editUserDataRequest.getBuildingNumber());
        residence.setStreet(editUserDataRequest.getStreet());

        user.setFirstName(editUserDataRequest.getFirstName());
        user.setLastName(editUserDataRequest.getLastName());
        user.setResidence(residence);

        userRepository.save(user);

        return ResponseEntity.ok("Updated Data Correctly");

    }

    public ResponseEntity<?> getStudents() {
        List<User> students = userRepository.findByRole(Role.STUDENT);
        return ResponseEntity.ok(students
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toSet()));
    }

}
