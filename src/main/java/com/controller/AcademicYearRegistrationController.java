package com.controller;

import com.model.Student;
import com.request.AcademicYearForm;
import com.request.StudentInfoForm;
import com.service.AcademicYearRegistrationService;
import com.service.StudentService;
import com.service.ValidatorService;
import com.utils.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
@CrossOrigin
public class AcademicYearRegistrationController {

    private AcademicYearRegistrationService academicYearRegistrationService;
    private StudentService studentService;
    private ValidatorService validatorService;


    public AcademicYearRegistrationController(AcademicYearRegistrationService academicYearRegistrationService,
                                              StudentService studentService, ValidatorService validatorService) {
        this.academicYearRegistrationService = academicYearRegistrationService;
        this.studentService = studentService;
        this.validatorService = validatorService;
    }

    @PostMapping(path = "/student-form", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submitStudentInfoForm(@RequestBody StudentInfoForm studentInfoForm) {
        if(!validatorService.checkStudentInfoForm(studentInfoForm)) {
            return new ResponseEntity<>(MessageUtil.INVALID_FORM, HttpStatus.BAD_REQUEST);
        }

        return academicYearRegistrationService.submitAcademicYearRegistration(studentInfoForm);
    }

}
