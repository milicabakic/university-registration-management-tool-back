package com.request;

import lombok.Data;

@Data
public class NewAcademicYearForm {

    private int academicYear;
    private String academicProgramCode;
    private boolean renewed;

}
