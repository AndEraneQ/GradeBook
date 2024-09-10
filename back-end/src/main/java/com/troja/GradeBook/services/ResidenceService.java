package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.exception.resource.ResourceNotFoundException;
import com.troja.GradeBook.mapper.ResidenceMapper;
import com.troja.GradeBook.repository.ResidenceRepository;
import com.troja.GradeBook.services.IServices.IResidenceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResidenceService implements IResidenceService {

    private static final Logger logger = LoggerFactory.getLogger(ResidenceService.class);

    private final ResidenceRepository residenceRepository;
    private final ResidenceMapper residenceMapper;

    @Override
    public ResponseEntity<ResidenceDto> getUserResidence(Long userId) {
        return residenceRepository.findByUserId(userId)
                .map(residenceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    logger.error("Residence not found for userId: {}", userId);
                    return new ResourceNotFoundException("Couldn't find residence for userId: " + userId);
                });
    }
}
