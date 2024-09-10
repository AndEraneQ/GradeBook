package com.troja.GradeBook.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResidenceDto {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotNull(message = "Building number cannot be null")
    @Min(value = 1, message = "Building number must be greater than 0")
    private Long buildingNumber;

    @Min(value = 1, message = "Apartment number must be greater than 0")
    private Long apartmentNumber;
}
