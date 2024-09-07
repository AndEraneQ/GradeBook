package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.SubjectAndClassroomDto;
import com.troja.GradeBook.dto.TeacherSubjectClassDto;
import com.troja.GradeBook.entity.TeacherSubjectClass;
import com.troja.GradeBook.mapper.ClassroomMapper;
import com.troja.GradeBook.mapper.SubjectMapper;
import com.troja.GradeBook.mapper.TeacherSubjectClassMapper;
import com.troja.GradeBook.repository.ClassroomRepository;
import com.troja.GradeBook.repository.SubjectRepository;
import com.troja.GradeBook.repository.TeacherRepository;
import com.troja.GradeBook.repository.TeacherSubjectClassRepository;
import com.troja.GradeBook.services.IServices.ITeacherSubjectClassService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherSubjectClassService implements ITeacherSubjectClassService {

    private final TeacherSubjectClassRepository teacherSubjectClassRepository;
    private final TeacherSubjectClassMapper teacherSubjectClassMapper;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;
    private final SubjectMapper subjectMapper;

    @Override
    public ResponseEntity<List<TeacherSubjectClassDto>> findConnectionByClassId(Long classId) {
        List<TeacherSubjectClass> teacherSubjectClasses = teacherSubjectClassRepository.findByClassroomId(classId);
        List<TeacherSubjectClassDto> teacherSubjectClassDtos = teacherSubjectClasses.stream()
                .map(teacherSubjectClassMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teacherSubjectClassDtos);
    }

    @Override
    public ResponseEntity<String> updateConnection(TeacherSubjectClassDto teacherSubjectClassDto) {
        TeacherSubjectClass teacherSubjectClass = teacherSubjectClassRepository
                .findByClassroomIdAndSubjectId(teacherSubjectClassDto.getClassId(), teacherSubjectClassDto.getSubjectId())
                .orElse(new TeacherSubjectClass());

        if (teacherSubjectClassDto.getTeacherId() == null) {
            if (teacherSubjectClass.getId() != null) {
                teacherSubjectClassRepository.delete(teacherSubjectClass);
                return ResponseEntity.ok("Teacher assignment removed successfully");
            }
        } else {
            teacherSubjectClass.setTeacher(teacherRepository.findById(teacherSubjectClassDto.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found")));

            if (teacherSubjectClass.getId() == null) {
                teacherSubjectClass.setClassroom(classroomRepository.findById(teacherSubjectClassDto.getClassId())
                        .orElseThrow(() -> new RuntimeException("Classroom not found")));
                teacherSubjectClass.setSubject(subjectRepository.findById(teacherSubjectClassDto.getSubjectId())
                        .orElseThrow(() -> new RuntimeException("Subject not found")));
            }

            teacherSubjectClassRepository.save(teacherSubjectClass);
            return ResponseEntity.ok("Updated Teacher successfully");
        }
        return ResponseEntity.ok("No changes made");
    }

    @Override
    public ResponseEntity<List<SubjectAndClassroomDto>> getAllClassesAndSubjectsLearningByTeacher(Long teacherId) {
        List<TeacherSubjectClass> teacherSubjectClasses = teacherSubjectClassRepository.findByTeacherId(teacherId);
        List<SubjectAndClassroomDto> subjectAndClassroomDtos = teacherSubjectClasses.stream()
                .map(tsc -> new SubjectAndClassroomDto(classroomMapper.toDto(tsc.getClassroom()), subjectMapper.toDto(tsc.getSubject())))
                .collect(Collectors.toList());
        return ResponseEntity.ok(subjectAndClassroomDtos);
    }
}
