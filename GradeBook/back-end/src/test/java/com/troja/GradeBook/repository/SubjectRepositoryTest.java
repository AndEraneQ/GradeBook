package com.troja.GradeBook.repository;

import com.troja.GradeBook.entity.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SubjectRepositoryTest extends BaseTest{

    @Autowired
    private SubjectRepository underTest;

    @BeforeEach
    void setData(){
        Subject subject = new Subject(1L,"Mathematic",new HashSet<>());
        underTest.save(subject);
    }

    @AfterEach
    void removeData(){
        underTest.deleteAll();
    }

    @Test
    void shouldReturnTrueWhenExistByNameIsPresent(){
        //given
        //when
        boolean exist = underTest.existsByName("Mathematic");
        //then
        assertThat(exist).isTrue();
    }

    @Test
    void shouldReturnFalseWhenExistByNameIsNotPresent(){
        //given
        //when
        boolean exist = underTest.existsByName("Biology");
        //then
        assertThat(exist).isFalse();
    }

    @Test
    void shouldReturnSubjectWhenFindByNameIsPresent(){
        //given
        //when
        Optional<Subject> subjectByName = underTest.findByName("Mathematic");
        //then
        assertThat(subjectByName).isPresent();
    }

    @Test
    void shouldNotReturnSubjectWhenFindByNameIsNotPresent(){
        //given
        //when
        Optional<Subject> subjectByName = underTest.findByName("Biology");
        //then
        assertThat(subjectByName).isNotPresent();
    }

}