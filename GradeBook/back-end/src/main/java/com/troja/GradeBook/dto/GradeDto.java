package com.troja.GradeBook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GradeDto {
    private Long id;
    private int value;
    private Date date;
    private String description;
}
