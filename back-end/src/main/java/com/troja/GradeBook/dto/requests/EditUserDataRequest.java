package com.troja.GradeBook.dto.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditUserDataRequest {

    private Long userId;

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotEmpty(message = "Street cannot be empty")
    private String street;

    @Min(value = 1, message = "Building number must be greater than 0")
    private Long buildingNumber;

    @Min(value = 1, message = "Apartment number must be greater than 0")
    private Long apartmentNumber;

    private String password;

    private String confirmPassword;
}
