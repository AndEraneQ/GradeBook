package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.entity.Residence;
import com.troja.GradeBook.exception.MyCustomException;
import com.troja.GradeBook.mapper.ResidenceMapper;
import com.troja.GradeBook.repository.ResidenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ResidenceService {

    private ResidenceRepository residenceRepository;
    private ResidenceMapper residenceMapper;

    public ResponseEntity<ResidenceDto> getUserResidence(Long userId) {
        Residence residence = residenceRepository.findByUserId(userId)
                .orElseThrow(() -> new MyCustomException("error","couldn't find residence"));
        return ResponseEntity.ok(residenceMapper.toDto(residence));
    }
}
