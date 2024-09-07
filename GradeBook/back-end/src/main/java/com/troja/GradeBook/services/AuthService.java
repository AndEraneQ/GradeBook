package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.AuthenticateDto;
import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.dto.requests.RegisterUserRequest;
import com.troja.GradeBook.entity.Classroom;
import com.troja.GradeBook.entity.Residence;
import com.troja.GradeBook.entity.Role;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.exception.user.UserNotFoundException;
import com.troja.GradeBook.exception.user.UserRegistrationException;
import com.troja.GradeBook.exception.validation.ValidationException;
import com.troja.GradeBook.mapper.ResidenceMapper;
import com.troja.GradeBook.mapper.UserMapper;
import com.troja.GradeBook.repository.ClassroomRepository;
import com.troja.GradeBook.repository.ResidenceRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import com.troja.GradeBook.repository.UserRepository;
import com.troja.GradeBook.security.LoginResponse;
import com.troja.GradeBook.security.UserDetails.UserDetailsImpl;
import com.troja.GradeBook.security.jwt.JwtUtils;
import com.troja.GradeBook.services.IServices.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService implements IAuthService {

    private final UserMapper userMapper;
    private final ResidenceMapper residenceMapper;
    private final UserRepository userRepository;
    private final ResidenceRepository residenceRepository;
    private final ClassroomRepository classroomRepository;
    private final TeacherRepository teacherRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity<LoginResponse> login(AuthenticateDto authenticateDto) {
        User authenticatedUser = authenticateUser(authenticateDto);
        String jwtToken = generateJwtToken(authenticatedUser);

        LoginResponse loginResponse = createLoginResponse(authenticatedUser, jwtToken);
        return ResponseEntity.ok(loginResponse);
    }

    @Override
    public ResponseEntity<String> register(RegisterUserRequest registerUserRequest) {
        validateUserEmail(registerUserRequest.getUser().getEmail());
        validateStudentClass(registerUserRequest.getUser());

        User user = mapAndPrepareUser(registerUserRequest);
        userRepository.save(user);

        handleTeacherRegistration(user);
        handleResidenceRegistration(user, registerUserRequest.getResidence());

        return ResponseEntity.ok("Registered successfully.");
    }

    private User mapAndPrepareUser(RegisterUserRequest registerUserRequest) {
        User user = userMapper.toEntity(registerUserRequest.getUser());
        Classroom classroom = findClassroomIfStudent(user);
        user.setClassroom(classroom);
        user.setPassword(passwordEncoder.encode("Password"));
        return user;
    }

    private Classroom findClassroomIfStudent(User user) {
        if (user.getRole().equals(Role.STUDENT)) {
            return classroomRepository.findByName(user.getClassroom().getName());
        }
        return null;
    }


    private User authenticateUser(AuthenticateDto authenticateDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticateDto.getEmail(),
                            authenticateDto.getPassword()
                    )
            );
            return userRepository.findByEmail(authenticateDto.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("User not found with email: " + authenticateDto.getEmail()));
        } catch (AuthenticationException e) {
            throw new ValidationException("Invalid credentials");
        }
    }

    private String generateJwtToken(User authenticatedUser) {
        UserDetails userDetails = new UserDetailsImpl(authenticatedUser);
        return jwtUtils.generateToken(userDetails);
    }

    public LoginResponse createLoginResponse(User authenticatedUser, String jwtToken) {
        Classroom classroom = authenticatedUser.getClassroom();
        String classroomName = (classroom != null) ? classroom.getName() : null;
        Long classroomId = (classroom != null) ? classroom.getId() : null;

        boolean passwordWasChanged = !passwordEncoder.matches("Password", authenticatedUser.getPassword());

        return new LoginResponse(
                authenticatedUser.getId(),
                jwtToken,
                authenticatedUser.getFirstName(),
                authenticatedUser.getLastName(),
                authenticatedUser.getEmail(),
                classroomName,
                classroomId,
                passwordWasChanged,
                authenticatedUser.getRole()
        );
    }

    private void validateUserEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserRegistrationException("Email already exists.");
        }
    }

    private void validateStudentClass(UserDto userDto) {
        if (userDto.getRole().equals(Role.STUDENT) && !classroomRepository.existsByName(userDto.getClassName())) {
            throw new ValidationException("Class " + userDto.getClassName() + " does not exist!");
        }
    }

    private void handleTeacherRegistration(User user) {
        if (user.getRole().equals(Role.TEACHER)) {
            Teacher teacher = new Teacher();
            teacher.setUser(user);
            teacherRepository.save(teacher);
        }
    }

    private void handleResidenceRegistration(User user, ResidenceDto residenceDto) {
        Residence residence = residenceMapper.toEntity(residenceDto);
        residence.setUser(user);
        residenceRepository.save(residence);
    }
}
