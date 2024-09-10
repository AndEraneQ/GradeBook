package com.troja.GradeBook.services.IServices;

import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.dto.requests.EditClassRequest;
import org.springframework.http.ResponseEntity;

public interface IClassroomService {

    ResponseEntity<?> findAllClasses();

    ResponseEntity<?> addClass(String className, TeacherDto teacherDto);

    ResponseEntity<?> findStudentsOfClass(Long classId);

    ResponseEntity<?> deleteClass(Long classId);

    ResponseEntity<?> editClassroom(EditClassRequest editClassRequest);
}