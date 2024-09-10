package com.troja.GradeBook.repository;

import com.troja.GradeBook.entity.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long> {
    Optional<Residence> findByUserId(Long userId);
}
