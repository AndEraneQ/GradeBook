package com.troja.GradeBook.repository;

import com.troja.GradeBook.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findAll();
    Optional<Teacher> findById(Long id);
    @Query("SELECT t FROM Teacher t JOIN t.subjects s WHERE s.id = :subjectId")
    List<Teacher> findTeachersBySubjectId(@Param("subjectId") Long subjectId);
    Optional<Teacher> findByUserId(Long userId);
}
