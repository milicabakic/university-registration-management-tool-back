package com.request;

import lombok.Data;

@Data
public class StudentInfoForm {

    private String fullName;

    private int currESPB;
    private int wantedESPB;
    private int indexNumber;
    private int indexYear;
    private String academicProgramCode;

    private int nextAcademicYear;
    private boolean renewedYear;
    private String chosenAcademicProgramCode;

}
