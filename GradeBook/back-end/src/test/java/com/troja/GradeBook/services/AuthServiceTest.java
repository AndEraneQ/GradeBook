package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.AuthenticateDto;
import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.dto.requests.RegisterUserRequest;
import com.troja.GradeBook.entity.Classroom;
import com.troja.GradeBook.entity.Residence;
import com.troja.GradeBook.entity.Role;
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
import com.troja.GradeBook.security.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ResidenceRepository residenceRepository;

    @Mock
    private ClassroomRepository classroomRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private ResidenceMapper residenceMapper;

    @Mock
    private TeacherRepository teacherRepository;

    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail("test@example.com");
        mockUser.setFirstName("Test");
        mockUser.setLastName("User");
        mockUser.setRole(Role.STUDENT);

        Classroom mockClassroom = new Classroom();
        mockClassroom.setName("1A");
        mockClassroom.setId(1L);
        mockUser.setClassroom(mockClassroom);
    }

    @Test
    public void testLogin_Success() {
        AuthenticateDto authenticateDto = new AuthenticateDto("test@example.com", "password");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));

        Authentication authenticationMock = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authenticationMock);

        String jwtToken = "mockJwtToken";
        when(jwtUtils.generateToken(any())).thenReturn(jwtToken);

        ResponseEntity<LoginResponse> response = authService.login(authenticateDto);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("test@example.com", response.getBody().getEmail());
        assertEquals(jwtToken, response.getBody().getToken());
    }

    @Test
    public void testLogin_NoClassroom_Success(){
        mockUser.setClassroom(null);
        AuthenticateDto authenticateDto = new AuthenticateDto("test@example.com", "password");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));

        Authentication authenticationMock = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authenticationMock);

        String jwtToken = "mockJwtToken";
        when(jwtUtils.generateToken(any())).thenReturn(jwtToken);

        ResponseEntity<LoginResponse> response = authService.login(authenticateDto);

        assertNull(response.getBody().getClassName());
        assertNull(response.getBody().getClassId());
    }

    @Test
    public void testLogin_UserNotFound_Failure() {
        AuthenticateDto authenticateDto = new AuthenticateDto("test@example.com", "wrongPassword");
        when(userRepository.findByEmail(authenticateDto.getEmail())).thenReturn(Optional.empty());

        UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () -> {
            authService.login(authenticateDto);
        });

        assertEquals("User not found with email: " + authenticateDto.getEmail(), userNotFoundException.getMessage());
    }

    @Test
    public void testLogin_BadCredentials_Failure() {
        AuthenticateDto authenticateDto = new AuthenticateDto("test@example.com", "wrongPassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AuthenticationException("Invalid credentials") {});

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            authService.login(authenticateDto);
        });

        assertEquals("Invalid credentials", thrown.getMessage());
    }

    @Test
    public void testRegister_EmailAlreadyExists_Failure(){

        RegisterUserRequest registerUserRequest = new RegisterUserRequest(new UserDto(), new ResidenceDto());

        when(userRepository.existsByEmail(any())).thenReturn(true);

        UserRegistrationException thrown = assertThrows(UserRegistrationException.class, () -> {
            authService.register(registerUserRequest);
        });

        assertEquals("Email already exists.", thrown.getMessage());
    }

    @Test
    public void testRegister_ClassNotFound_Failure() {
        when(userMapper
                .toDto(mockUser))
                .thenReturn(new UserDto(
                        mockUser.getId(),
                        mockUser.getEmail(),
                        mockUser.getFirstName(),
                        mockUser.getLastName(),
                        mockUser.getClass().getName(),
                        mockUser.getRole()
                ));

        UserDto userDto = userMapper.toDto(mockUser);
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(userDto, new ResidenceDto());

        when(classroomRepository.existsByName(any())).thenReturn(false);

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            authService.register(registerUserRequest);
        });

        assertEquals("Class " + userDto.getClassName() + " does not exist!", thrown.getMessage());
    }

    @Test
    public void testRegister_TeacherRole_Success() {
        UserDto userDto = new UserDto();
        userDto.setEmail("teacher@example.com");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setRole(Role.TEACHER);

        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setCity("Cracow");
        residenceDto.setStreet("Jana Pawła");
        residenceDto.setApartmentNumber(1L);
        residenceDto.setBuildingNumber(22L);

        RegisterUserRequest registerUserRequest = new RegisterUserRequest(userDto, residenceDto);

        User user = new User();
        user.setRole(userDto.getRole());
        Residence residence = new Residence();

        when(residenceMapper.toEntity(residenceDto)).thenReturn(residence);
        when(userMapper.toEntity(userDto)).thenReturn(user);
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);

        authService.register(registerUserRequest);

        verify(userRepository, times(1)).save(any(User.class));
        verify(residenceRepository, times(1)).save(any(Residence.class));
        verify(teacherRepository, times(1)).save(any());
    }

    @Test
    public void testRegister_StudentRole_Success() {
        UserDto userDto = new UserDto();
        userDto.setEmail("student@example.com");
        userDto.setFirstName("Alice");
        userDto.setLastName("Smith");
        userDto.setRole(Role.STUDENT);

        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setCity("Cracow");
        residenceDto.setStreet("Jana Pawła");
        residenceDto.setApartmentNumber(1L);
        residenceDto.setBuildingNumber(22L);

        RegisterUserRequest registerUserRequest = new RegisterUserRequest(userDto, residenceDto);

        Classroom classroom = new Classroom();
        classroom.setName("1A");

        User user = new User();
        user.setRole(userDto.getRole());
        user.setClassroom(classroom);
        Residence residence = new Residence();

        when(residenceMapper.toEntity(residenceDto)).thenReturn(residence);
        when(userMapper.toEntity(userDto)).thenReturn(user);
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
        when(classroomRepository.existsByName(any())).thenReturn(true);

        authService.register(registerUserRequest);

        verify(userRepository, times(1)).save(any(User.class));
        verify(residenceRepository, times(1)).save(any(Residence.class));
        verify(teacherRepository, times(0)).save(any());
    }


    @Test
    public void testRegister_AdminRole_Success() {
        UserDto userDto = new UserDto();
        userDto.setEmail("admin@example.com");
        userDto.setFirstName("Admin");
        userDto.setLastName("User");
        userDto.setRole(Role.ADMIN);

        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setCity("Cracow");
        residenceDto.setStreet("Jana Pawła");
        residenceDto.setApartmentNumber(1L);
        residenceDto.setBuildingNumber(22L);

        RegisterUserRequest registerUserRequest = new RegisterUserRequest(userDto, residenceDto);

        User user = new User();
        user.setRole(userDto.getRole());
        Residence residence = new Residence();

        when(residenceMapper.toEntity(residenceDto)).thenReturn(residence);
        when(userMapper.toEntity(userDto)).thenReturn(user);
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);

        authService.register(registerUserRequest);

        verify(userRepository, times(1)).save(any(User.class));
        verify(residenceRepository, times(1)).save(any(Residence.class));
        verify(teacherRepository, times(0)).save(any());
    }
}
