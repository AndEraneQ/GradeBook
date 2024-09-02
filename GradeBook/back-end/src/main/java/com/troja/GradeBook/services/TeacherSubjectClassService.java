package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.SubjectAndClassroomDto;
import com.troja.GradeBook.dto.TeacherSubjectClassDto;
import com.troja.GradeBook.entity.TeacherSubjectClass;
import com.troja.GradeBook.mapper.ClassroomMapper;
import com.troja.GradeBook.mapper.SubjectMapper;
import com.troja.GradeBook.mapper.TeacherMapper;
import com.troja.GradeBook.mapper.TeacherSubjectClassMapper;
import com.troja.GradeBook.repository.ClassroomRepository;
import com.troja.GradeBook.repository.SubjectRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import com.troja.GradeBook.repository.TeacherSubjectClassRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherSubjectClassService {
    private TeacherSubjectClassRepository teacherSubjectClassRepository;
    private TeacherSubjectClassMapper teacherSubjectClassMapper;
    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;
    private ClassroomRepository classroomRepository;
    private TeacherMapper teacherMapper;
    private SubjectMapper subjectMapper;
    private ClassroomMapper classroomMapper;

    public ResponseEntity<?> findConnectionByClassId(Long classId){
        List<TeacherSubjectClass> teacherSubjectClass =
                teacherSubjectClassRepository.findByClassroomId(classId);

        return ResponseEntity.ok(teacherSubjectClass
                .stream()
                .map(teacherSubjectClassMapper::toDto)
                .collect(Collectors.toList()));
    }

    public ResponseEntity<String> updateConnection(TeacherSubjectClassDto teacherSubjectClassDto) {
        TeacherSubjectClass teacherSubjectClass = teacherSubjectClassRepository
                .findByClassroomIdAndSubjectId(
                        teacherSubjectClassDto.getClassId(),
                        teacherSubjectClassDto.getSubjectId()
                )
                .orElseGet(TeacherSubjectClass::new);

        if (teacherSubjectClassDto.getTeacherId() == null) {
            if (teacherSubjectClass.getId() != null) {
                teacherSubjectClassRepository.delete(teacherSubjectClass);
                return ResponseEntity.ok("Teacher assignment removed successfully");
            }
        } else {
            teacherSubjectClass.setTeacher(
                    teacherRepository.findById(teacherSubjectClassDto.getTeacherId())
                            .orElseThrow(() -> new RuntimeException("Teacher not found"))
            );

            if (teacherSubjectClass.getId() == null) {
                teacherSubjectClass.setClassroom(
                        classroomRepository.findById(teacherSubjectClassDto.getClassId())
                                .orElseThrow(() -> new RuntimeException("Classroom not found"))
                );
                teacherSubjectClass.setSubject(
                        subjectRepository.findById(teacherSubjectClassDto.getSubjectId())
                                .orElseThrow(() -> new RuntimeException("Subject not found"))
                );
            }

            teacherSubjectClassRepository.save(teacherSubjectClass);
            return ResponseEntity.ok("Updated Teacher correctly");
        }
        return ResponseEntity.ok("No changes made");
    }

    public ResponseEntity<?> getAllClassesAndSubjectsLearningByTeacher(Long teacherId){
        List<TeacherSubjectClass> teacherSubjectClasses = teacherSubjectClassRepository.findByTeacherId(teacherId);
        List<SubjectAndClassroomDto> subjectAndClassroomDtos = new ArrayList<>();
        for(TeacherSubjectClass tsc : teacherSubjectClasses){
            SubjectAndClassroomDto subjectAndClassroomDto =
                    new SubjectAndClassroomDto(
                            classroomMapper.toDto(tsc.getClassroom()),
                            subjectMapper.toDto(tsc.getSubject())
                    );
            subjectAndClassroomDtos.add(subjectAndClassroomDto);
        }
        return ResponseEntity.ok(subjectAndClassroomDtos);
    }
}
