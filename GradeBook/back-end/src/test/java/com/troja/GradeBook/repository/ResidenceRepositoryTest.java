package com.troja.GradeBook.repository;

import com.troja.GradeBook.entity.Residence;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ResidenceRepositoryTest {

    @Autowired
    private ResidenceRepository residenceRepository;

    @Container
    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0");

    @Test
    void shouldReturnResidenceWhenFindByUserId() {
        //given
        Residence residence = new Residence(22L,
                "Cracow",
                "Jana Matejki",
                22L,
                22L,
                22L);
        residenceRepository.save(residence);
        //when
        Optional<Residence> residenceById = residenceRepository.findByUserId(22L);
        //then
        assertThat(residenceById.isPresent());
    }
}