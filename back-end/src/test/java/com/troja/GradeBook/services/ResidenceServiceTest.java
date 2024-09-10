package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.entity.Residence;
import com.troja.GradeBook.exception.resource.ResourceNotFoundException;
import com.troja.GradeBook.mapper.ResidenceMapper;
import com.troja.GradeBook.repository.ResidenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResidenceServiceTest {

    @Mock
    private ResidenceRepository residenceRepository;

    @Mock
    private ResidenceMapper residenceMapper;

    @InjectMocks
    private ResidenceService residenceService;

    @Test
    void getUserResidence_ReturnsResidenceDto_WhenResidenceExists() {
        Long userId = 1L;
        Residence residence = new Residence();
        ResidenceDto residenceDto = new ResidenceDto();

        when(residenceRepository.findByUserId(userId)).thenReturn(Optional.of(residence));
        when(residenceMapper.toDto(residence)).thenReturn(residenceDto);

        ResponseEntity<ResidenceDto> response = residenceService.getUserResidence(userId);

        assertEquals(ResponseEntity.ok(residenceDto), response);
        verify(residenceRepository).findByUserId(userId);
        verify(residenceMapper).toDto(residence);
    }

    @Test
    void getUserResidence_ThrowsResourceNotFoundException_WhenResidenceDoesNotExist() {
        Long userId = 1L;
        when(residenceRepository.findByUserId(userId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            residenceService.getUserResidence(userId);
        });

        assertEquals("Couldn't find residence for userId: " + userId, exception.getMessage());
        verify(residenceRepository).findByUserId(userId);
        verify(residenceMapper, never()).toDto(any());
    }
}
