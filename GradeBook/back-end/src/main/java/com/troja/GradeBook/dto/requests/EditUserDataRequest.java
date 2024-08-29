package com.troja.GradeBook.dto.requests;

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
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private Long apartmentNumber;
    private Long buildingNumber;
    private String password;
    private String confirmPassword;
}
