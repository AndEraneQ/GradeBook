package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.mapper.TeacherMapper;
import com.troja.GradeBook.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherMapper teacherMapper;

    public ResponseEntity<?> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        if(teachers.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<TeacherDto> teacherDtos = teachers
                .stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teacherDtos);
    }

    public ResponseEntity<?> getAllTeachersOfSubject(Long subjectId){
        List<Teacher> listOfTeachers = teacherRepository.findTeachersBySubjectId(subjectId);
        List<TeacherDto> teachersDtos = listOfTeachers
                .stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teachersDtos);
    }
}