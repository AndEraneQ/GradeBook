package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.SubjectDto;
import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.dto.requests.AddSubjectRequest;
import com.troja.GradeBook.dto.requests.EditSubjectRequest;
import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.mapper.SubjectMapper;
import com.troja.GradeBook.repository.SubjectRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService {

    private SubjectRepository subjectRepository;
    private TeacherRepository teacherRepository;
    private SubjectMapper subjectMapper;

    private String formatSubjectName(String name) {
        if (name == null || name.isEmpty()) return null;
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    private void associateTeachersWithSubject(List<TeacherDto> listOfTeachers, Subject subject) {
        Set<Teacher> teachersOfSubject = new HashSet<>();

        for (TeacherDto teacherDto : listOfTeachers) {
            Teacher teacher = teacherRepository.findById(teacherDto.getId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + teacherDto.getId()));

            teachersOfSubject.add(teacher);

            teacher.getSubjects().add(subject);
        }

        subject.setTeachers(teachersOfSubject);
        subjectRepository.save(subject);
    }

    public List<SubjectDto> getAllSubject() {
        return subjectRepository.findAll().stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> addSubject(AddSubjectRequest addSubjectRequest) {
        String name = formatSubjectName(addSubjectRequest.getName());
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().body("Subject name cannot be empty.");
        }

        if (subjectRepository.existsByName(name)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Subject with name '" + name + "' already exists.");
        }

        Subject subject = new Subject(name);
        try {
            subjectRepository.save(subject);
            Subject subjectFromDatabase = subjectRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("Failed to retrieve the subject from the database."));

            associateTeachersWithSubject(addSubjectRequest.getListOfTeachers(), subjectFromDatabase);
        } catch (Exception ex) {
            String error = "Server error, try again later: " + ex.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }

        return ResponseEntity.ok("Added subject correctly");
    }


    public ResponseEntity<?> editSubjectData(EditSubjectRequest editSubjectRequest) {
        Subject subject = subjectRepository.findById(editSubjectRequest.getSubject().getId())
                .orElseThrow(() -> new NoSuchElementException("Subject not found"));

        String newName = editSubjectRequest.getSubject().getName();
        if (!subject.getName().equals(newName)) {
            subject.setName(newName);
        }

        for (TeacherDto teacherDto : editSubjectRequest.getAddedTeachers()) {
            Teacher teacher = teacherRepository.findById(teacherDto.getId())
                    .orElseThrow(() -> new NoSuchElementException("Teacher not found"));

            if (!subject.getTeachers().contains(teacher)) {
                subject.getTeachers().add(teacher);
                teacher.getSubjects().add(subject);
            }
        }

        for (TeacherDto teacherDto : editSubjectRequest.getDeletedTeachers()) {
            Teacher teacher = teacherRepository.findById(teacherDto.getId())
                    .orElseThrow(() -> new NoSuchElementException("Teacher not found"));

            if (subject.getTeachers().contains(teacher)) {
                subject.getTeachers().remove(teacher);
                teacher.getSubjects().remove(subject);
            }
        }

        subjectRepository.save(subject);
        return ResponseEntity.ok("Data for the subject has been updated successfully.");
    }

    public ResponseEntity<?> deleteSubject(Long subjectId){
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow( () -> new RuntimeException("Couldn't find subject"));

        for (Teacher teacher : subject.getTeachers()){
            teacher.getSubjects().remove(subject);
        }
        subjectRepository.delete(subject);

        return ResponseEntity.ok("Deleted " + subject.getName() + " correctly!");
    }
}
