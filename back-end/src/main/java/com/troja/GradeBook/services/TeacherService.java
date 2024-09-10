package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.mapper.TeacherMapper;
import com.troja.GradeBook.repository.TeacherRepository;
import com.troja.GradeBook.services.IServices.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> teacherDtos = teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teacherDtos);
    }

    @Override
    public ResponseEntity<List<TeacherDto>> getAllTeachersOfSubject(Long subjectId) {
        List<TeacherDto> teacherDtos = teacherRepository.findTeachersBySubjectId(subjectId)
                .stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teacherDtos);
    }

    @Override
    public ResponseEntity<TeacherDto> getTeacherFromUserId(Long userId) {
        Teacher teacher = teacherRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Couldn't find teacher with user ID: " + userId));
        return ResponseEntity.ok(teacherMapper.toDto(teacher));
    }
}
