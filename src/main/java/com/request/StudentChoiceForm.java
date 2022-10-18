package com.request;

import lombok.Data;

import java.util.List;

@Data
public class StudentChoiceForm {

    private Long registrationId;
    private List<Long> oldSubjects;
    private Long groupOddId;
    private Long groupEvenId;

}
