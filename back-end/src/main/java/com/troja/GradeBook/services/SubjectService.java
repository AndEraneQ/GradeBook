package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.SubjectDto;
import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.dto.requests.AddSubjectRequest;
import com.troja.GradeBook.dto.requests.EditSubjectRequest;
import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.entity.Teacher;
import com.troja.GradeBook.exception.resource.ResourceNotFoundException;
import com.troja.GradeBook.mapper.SubjectMapper;
import com.troja.GradeBook.repository.SubjectRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import com.troja.GradeBook.services.IServices.ISubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService implements ISubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> addSubject(AddSubjectRequest addSubjectRequest) {
        String name = validateSubjectName(addSubjectRequest.getName());

        Subject subject = new Subject(name);
        subjectRepository.save(subject);
        associateTeachersWithSubject(addSubjectRequest.getListOfTeachers(), subject);

        return ResponseEntity.ok("Added subject correctly");
    }

    @Override
    public ResponseEntity<String> editSubjectData(EditSubjectRequest editSubjectRequest) {
        Subject subject = findSubjectById(editSubjectRequest.getSubject().getId());

        updateSubjectName(subject, editSubjectRequest.getSubject().getName());
        updateSubjectTeachers(subject, editSubjectRequest.getAddedTeachers(), editSubjectRequest.getDeletedTeachers());

        subjectRepository.save(subject);
        return ResponseEntity.ok("Data for the subject has been updated successfully.");
    }

    @Override
    public ResponseEntity<String> deleteSubject(Long subjectId) {
        Subject subject = findSubjectById(subjectId);
        removeTeachersFromSubject(subject);
        subjectRepository.delete(subject);

        return ResponseEntity.ok("Deleted " + subject.getName() + " correctly!");
    }

    private String validateSubjectName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Subject name cannot be empty.");
        }
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        if (subjectRepository.existsByName(name)) {
            throw new IllegalArgumentException("Subject with name '" + name + "' already exists.");
        }
        return name;
    }

    private void updateSubjectName(Subject subject, String newName) {
        if (!subject.getName().equals(newName)) {
            subject.setName(newName);
        }
    }

    private void updateSubjectTeachers(Subject subject, List<TeacherDto> addedTeachers, List<TeacherDto> deletedTeachers) {
        for (TeacherDto teacherDto : addedTeachers) {
            Teacher teacher = findTeacherById(teacherDto.getId());
            if (!subject.getTeachers().contains(teacher)) {
                subject.getTeachers().add(teacher);
                teacher.getSubjects().add(subject);
            }
        }

        for (TeacherDto teacherDto : deletedTeachers) {
            Teacher teacher = findTeacherById(teacherDto.getId());
            subject.getTeachers().remove(teacher);
            teacher.getSubjects().remove(subject);
        }
    }

    private Subject findSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find subject with ID: " + subjectId));
    }

    private Teacher findTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + teacherId));
    }

    private void associateTeachersWithSubject(List<TeacherDto> listOfTeachers, Subject subject) {
        Set<Teacher> teachersOfSubject = new HashSet<>();
        for (TeacherDto teacherDto : listOfTeachers) {
            Teacher teacher = findTeacherById(teacherDto.getId());
            teachersOfSubject.add(teacher);
            teacher.getSubjects().add(subject);
        }
        subject.setTeachers(teachersOfSubject);
        subjectRepository.save(subject);
    }

    private void removeTeachersFromSubject(Subject subject) {
        for (Teacher teacher : subject.getTeachers()) {
            teacher.getSubjects().remove(subject);
        }
    }
}
