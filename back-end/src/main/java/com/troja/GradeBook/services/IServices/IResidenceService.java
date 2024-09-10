package com.troja.GradeBook.services.IServices;

import com.troja.GradeBook.dto.ResidenceDto;
import org.springframework.http.ResponseEntity;

public interface IResidenceService {
    public ResponseEntity<ResidenceDto> getUserResidence(Long userId);
}
