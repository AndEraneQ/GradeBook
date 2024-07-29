package com.troja.GradeBook.controllers;

import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.services.ResidenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ResidenceController {

    private final ResidenceService residenceService;

    @GetMapping("/residence/{id}")
    public ResponseEntity<ResidenceDto> getUserResidence(@PathVariable Long id){
        return residenceService.getUserResidence(id);
    }
}
