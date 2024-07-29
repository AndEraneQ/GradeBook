package com.troja.GradeBook.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResidenceDto {
    private String city;
    private String street;
    private Long buildingNumber;
    private Long apartmentNumber;
}
