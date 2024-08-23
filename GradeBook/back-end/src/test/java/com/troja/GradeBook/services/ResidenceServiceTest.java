package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.entity.Residence;
import com.troja.GradeBook.mapper.ResidenceMapper;
import com.troja.GradeBook.repository.ResidenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResidenceServiceTest {

    @InjectMocks
    private ResidenceService residenceService;

    @Mock
    private ResidenceRepository residenceRepository;

    @Mock
    private ResidenceMapper residenceMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserResidence_ShouldReturnResidenceDto_WhenResidenceExists() {
        // given
        Long userId = 1L;
        Residence residence = new Residence(1L, "Cracow", "Jana Matejki", 54L, 32L, userId);
        ResidenceDto residenceDto = new ResidenceDto("Cracow", "Jana Matejki", 54L, 32L);
        when(residenceRepository.findByUserId(userId)).thenReturn(Optional.of(residence));
        when(residenceMapper.toDto(residence)).thenReturn(residenceDto);

        // when
        ResponseEntity<ResidenceDto> response = residenceService.getUserResidence(userId);

        // then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(residenceDto);
        verify(residenceRepository).findByUserId(userId);
        verify(residenceMapper).toDto(residence);
    }

    @Test
    void getUserResidence_ShouldThrowMyCustomException_WhenResidenceDoesNotExist() {
        // given
        Long userId = 1L;
        when(residenceRepository.findByUserId(userId)).thenReturn(Optional.empty());

        // when
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> residenceService.getUserResidence(userId));

        // then
        assertThat(exception.getMessage()).isEqualTo("Couldn't find residence. Try again later");
        verify(residenceRepository).findByUserId(userId);
        verify(residenceMapper, never()).toDto(any());
    }
}