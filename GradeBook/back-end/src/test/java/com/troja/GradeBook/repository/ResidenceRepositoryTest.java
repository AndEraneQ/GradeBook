package com.troja.GradeBook.repository;

import com.troja.GradeBook.entity.Residence;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ResidenceRepositoryTest {

    @Autowired
    private ResidenceRepository residenceRepository;

    @Container
    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0");

    @BeforeEach
    void setUp(){
        Residence residence = new Residence(1L,
                "Cracow",
                "Jana Matejki",
                25L,
                90L,
                1L);
        residenceRepository.save(residence);
    }

    @AfterEach
    void tearDown(){
        residenceRepository.deleteAll();
    }

    @Test
    void canEstablishedConnection(){
        assertThat(mySQLContainer.isCreated()).isTrue();
        assertThat(mySQLContainer.isRunning()).isTrue();
    }

    @Test
    void shouldReturnResidenceWhenFindByUserId() {
        //given
        //when
        Optional<Residence> residenceById = residenceRepository.findByUserId(1L);
        //then
        assertThat(residenceById).isPresent();
    }

    @Test
    void shouldNotReturnResidenceWhenFindByUserIdIsNotPresent() {
        //given
        //when
        Optional<Residence> residenceById = residenceRepository.findByUserId(2L);
        //then
        assertThat(residenceById).isNotPresent();
    }
}