package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.AuthenticateDto;
import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.dto.requests.RegisterUserRequest;
import com.troja.GradeBook.entity.*;
import com.troja.GradeBook.exception.MyCustomException;
import com.troja.GradeBook.mapper.ResidenceMapper;
import com.troja.GradeBook.mapper.UserMapper;
import com.troja.GradeBook.repository.ClassroomRepository;
import com.troja.GradeBook.repository.ResidenceRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import com.troja.GradeBook.repository.UserRepository;
import com.troja.GradeBook.security.LoginResponse;
//import com.troja.GradeBook.security.UserDetails.UserDetailsImpl;
import com.troja.GradeBook.security.UserDetails.UserDetailsImpl;
import com.troja.GradeBook.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserMapper userMapper;
    private final ResidenceMapper residenceMapper;
    private final UserRepository userRepository;
    private final ResidenceRepository residenceRepository;
    private final ClassroomRepository classroomRepository;
    private final TeacherRepository teacherRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public ResponseEntity<LoginResponse> login(AuthenticateDto authenticateDto) {
        User authenticatedUser;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticateDto.getEmail(),
                            authenticateDto.getPassword()
                    )
            );
            authenticatedUser = userRepository.findByEmail(authenticateDto.getEmail())
                    .orElseThrow(() -> new MyCustomException("email", "User not found"));
        } catch (AuthenticationException e) {
            throw new MyCustomException("password", "Invalid credentials");
        }

        UserDetails userDetails = new UserDetailsImpl(authenticatedUser);
        String jwtToken = jwtUtils.generateToken(userDetails);
        LoginResponse loginResponse = new LoginResponse(
                authenticatedUser.getId(),
                jwtToken,
                authenticatedUser.getFirstName(),
                authenticatedUser.getLastName(),
                authenticatedUser.getEmail(),
                authenticatedUser.getRole()
        );

        return ResponseEntity.ok(loginResponse);
    }

    public ResponseEntity<?> register(RegisterUserRequest registerUserRequest) {
        UserDto userDto = registerUserRequest.getUser();
        ResidenceDto residenceDto = registerUserRequest.getResidence();

        if (userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already exists.");
        }
        if(userDto.getRole().equals(Role.STUDENT) && !classroomRepository.existsByName(userDto.getClassName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Class " + userDto.getClassName() + " not exists!");
        }

        User user = userMapper.toEntity(userDto);
        Classroom classroom = classroomRepository.findByName(userDto.getClassName());
        user.setPassword(passwordEncoder.encode("Password"));
        user.setClassroom(classroom);
        userRepository.save(user);
        Optional<User> savedUser = userRepository.findByEmail(userDto.getEmail());

        if (user.getRole().equals(Role.TEACHER)) {
            Teacher teacher = new Teacher();
            teacher.setUser(savedUser.get());
            teacherRepository.save(teacher);
        }

        if (savedUser.isPresent()) {
            Residence residence = residenceMapper.toEntity(residenceDto);
            residence.setUser(savedUser.get());
            residenceRepository.save(residence);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User registration failed.");
        }

        return ResponseEntity.ok("Registered successfully.");
    }
}

