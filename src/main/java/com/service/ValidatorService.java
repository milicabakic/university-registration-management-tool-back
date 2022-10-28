package com.service;

import com.request.StudentInfoForm;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    private static final String WHITESPACE = " ";

    public boolean checkStudentInfoForm(StudentInfoForm studentInfoForm) {
        if (studentInfoForm.getFullName().split(WHITESPACE).length < 2) {
            return false;
        }

        return true;
    }
}
