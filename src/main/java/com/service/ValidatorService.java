package com.service;

import com.request.StudentInfoForm;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    public boolean checkStudentInfoForm(StudentInfoForm studentInfoForm) {
        if(studentInfoForm.getFullName().split(" ").length < 2)
            return false;

        return true;
    }
}
