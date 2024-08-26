package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.ClassroomDto;
import com.troja.GradeBook.entity.Classroom;
import com.troja.GradeBook.mapper.ClassroomMapper;

import com.troja.GradeBook.repository.ClassroomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@AllArgsConstructor
@Service
public class ClassroomService {

    private ClassroomRepository classRepository;
    private ClassroomMapper classroomMapper;

    public ResponseEntity<?> findAllClasses() {
//        List<ClassroomDto> classes = classRepository.findAll()
//        .stream()
//        .map(classroomMapper::toDto)
//        .collect(Collectors.toList());
//        System.out.println(classes);
//
//        if(classes.isEmpty()){
//            return ResponseEntity
//                    .status(HttpStatus.NO_CONTENT)
//                    .body("There are no classes available. You can add them.");
//        }
        return ResponseEntity.ok(classRepository.findAll()
                .stream()
                .map(classroomMapper::toDto)
                .collect(Collectors.toList()));
    }
}

