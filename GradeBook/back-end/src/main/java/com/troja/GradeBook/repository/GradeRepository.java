package com.troja.GradeBook.repository;

import com.troja.GradeBook.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {
    List<Grade> findByUserIdAndSubjectId(Long userId, Long subjectId);
}
