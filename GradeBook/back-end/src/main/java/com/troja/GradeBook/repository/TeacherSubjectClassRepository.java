package com.troja.GradeBook.repository;

import com.troja.GradeBook.entity.TeacherSubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherSubjectClassRepository extends JpaRepository<TeacherSubjectClass,Long> {
    List<TeacherSubjectClass> findByClassroomId(Long classroomId);
    Optional<TeacherSubjectClass> findByClassroomIdAndSubjectId(Long classroomId, Long subjectId);
    List<TeacherSubjectClass> findByTeacherId(Long teacherId);
}
