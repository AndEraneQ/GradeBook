package com.troja.GradeBook.repository;

import com.troja.GradeBook.entity.Role;
import com.troja.GradeBook.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends BaseTest{

    @Autowired
    private UserRepository underTest;

    private Long savedUserId;

    @BeforeEach
    void setData(){
        User user = new User(null,
                "johny@gmail.com",
                "$2y$10$cd2wQXye5gQf1fv/EgbvguuiN3Bal2biQq4p4NxjFjbBEPY0jgTUG",
                "Johny",
                "Deep",
                Role.STUDENT,
                null,
                null,
                null,
                new ArrayList<>(),
                new HashSet<>(),
                new HashSet<>()
                );
        savedUserId = underTest.save(user).getId();
    }

    @AfterEach
    void removeData(){
        underTest.deleteAll();
    }

    @Test
    void ShouldReturnUserWhenFindByEmailIsPresent() {
        //given
        //when
        Optional<User> userByEmail = underTest.findByEmail("johny@gmail.com");
        //then
        assertThat(userByEmail).isPresent();
    }

    @Test
    void ShouldNotReturnUserWhenFindByEmailIsNotPresent() {
        //given
        //when
        Optional<User> userByEmail = underTest.findByEmail("cristiano@gmail.com");
        //then
        assertThat(userByEmail).isNotPresent();
    }

    @Test
    void ShouldReturnUserWhenFindByIdIsPresent() {
        //given
        //when
        Optional<User> userById = underTest.findById(savedUserId);
        //then
        assertThat(userById).isPresent();
    }

    @Test
    void ShouldNotReturnUserWhenFindByIdIsNotPresent() {
        //given
        //when
        Optional<User> userById = underTest.findById(2L);
        //then
        assertThat(userById).isNotPresent();
    }

    @Test
    void ShouldReturnTwoUsersWhenFindByRole() {
        //given
        User admin = new User(null,
                "Mariano@gmail.com",
                "1fv/EgbvguuiN3Bal2biQq4p4N$gbvguuiN3Bal2biQq4p4NxjFjbB",
                "Johny",
                "Deep",
                Role.ADMIN,
                null,
                null,
                null,
                new ArrayList<>(),
                new HashSet<>(),
                new HashSet<>()
        );
        User student = new User(null,
                "cristiano@gmail.com",
                "2y$10$cd2wQXye5gQf1fv/E$2y$10$cd2wQXye5gQfxjFjbBEPY0jgTUG",
                "Cristiano",
                "Ronaldo",
                Role.STUDENT,
                null,
                null,
                null,
                new ArrayList<>(),
                new HashSet<>(),
                new HashSet<>()
        );
        underTest.save(admin);
        underTest.save(student);

        //when
        List<User> usersByRole = underTest.findByRole(Role.STUDENT);
        //then
        assertThat(usersByRole.size()).isEqualTo(2);
        assertThat(!usersByRole.contains(admin)).isTrue();
    }

    @Test
    void ShouldReturnEmptyArrayWhenFindByRole() {
        //given
        //when
        List<User> usersByRole = underTest.findByRole(Role.ADMIN);
        //then
        assertThat(usersByRole.isEmpty()).isTrue();
    }

    @Test
    void ShouldReturnTrueWhenExistByIdIsPresent() {
        //given
        //when
        boolean exist = underTest.existsById(savedUserId);
        //then
        assertThat(exist).isTrue();
    }

    @Test
    void ShouldReturnFalseWhenExistByIdIsNotPresent() {
        //given
        //when
        boolean exist = underTest.existsById(2L);
        //then
        assertThat(exist).isFalse();
    }

}