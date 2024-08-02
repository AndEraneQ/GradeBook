package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.AddSubjectRequest;
import com.troja.GradeBook.dto.SubjectDto;
import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.mapper.SubjectMapper;
import com.troja.GradeBook.mapper.UserMapper;
import com.troja.GradeBook.repository.SubjectRepository;
//import com.troja.GradeBook.repository.TeacherSubjectRepository;
import com.troja.GradeBook.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService {

    private SubjectRepository subjectRepository;
    private UserMapper userMapper;
    private UserRepository userRepository;
    private SubjectMapper subjectMapper;

    public List<SubjectDto> getAllSubject(){
        return subjectRepository.findAll().stream()
                .map(subject -> subjectMapper.toDto(subject))
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> addSubject(AddSubjectRequest addSubjectRequest) {
        String name = addSubjectRequest.getName();
        List<UserDto> listOfTeachers = addSubjectRequest.getListOfTeachers();

        // TODO: SWAP FIRST CHAR TO UPPERCASE
        if (name != null && !name.isEmpty()) {
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
        // TODO: CHECK IF SUBJECT DONT EXIST IN THE DATABASE
        if(subjectRepository.existsByName(name)){
            return ResponseEntity.status(HttpStatus.CONFLICT) // 409 Conflict
                    .body("Subject with name '" + name + "' already exists.");
        }
        // TODO: CREATE NEW SUBJECT OBJECT
        Subject subject = new Subject(name);


        try {
            // TODO: SAVE NEW SUBJECT
            subjectRepository.save(subject);
            // TODO: TAKE SUBJECT FROM DATABASE TO GET SUBJECT ID
            subject = subjectRepository.findByName(name);
            // TODO: ADD TO TABLE SUBJECT_USER RELATION FOR ALL USERS
            for (UserDto userDto : listOfTeachers) {
                //TODO: CHECK IF TEACHER DON'T LEARN THIS SUBJECT
                User teacher = userMapper.toEntity(userDto);
                teacher.getSubjects().add(subject);
                userRepository.save(teacher);
            }
        } catch (DataIntegrityViolationException ex) {
            String error = "Data integrity violation: " + ex.getMessage();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        } catch (Exception ex) {
            String error = "Server error, try again later";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
        return ResponseEntity.ok("Added subject correctly");
    }
}
