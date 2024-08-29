package com.troja.GradeBook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResidenceDto {
    private Long id;
    private String city;
    private String street;
    private Long buildingNumber;
    private Long apartmentNumber;
}
