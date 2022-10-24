package com.dto;

import lombok.Data;

@Data
public class AcademicYearRegistrationDto {

    private Long id;
    private int academicYear;
    private String academicProgram;
    private boolean renewed;
}
