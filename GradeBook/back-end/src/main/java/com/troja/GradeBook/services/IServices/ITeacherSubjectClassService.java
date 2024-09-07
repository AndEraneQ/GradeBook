package com.troja.GradeBook.services.IServices;

import com.troja.GradeBook.dto.SubjectAndClassroomDto;
import com.troja.GradeBook.dto.TeacherSubjectClassDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITeacherSubjectClassService {

    ResponseEntity<List<TeacherSubjectClassDto>> findConnectionByClassId(Long classId);

    ResponseEntity<String> updateConnection(TeacherSubjectClassDto teacherSubjectClassDto);

    ResponseEntity<List<SubjectAndClassroomDto>> getAllClassesAndSubjectsLearningByTeacher(Long teacherId);
}
