package com.controller;

import com.request.NewAcademicYearForm;
import com.request.StudentChoiceForm;
import com.request.StudentInfoForm;
import com.service.AcademicYearRegistrationService;
import com.service.StudentService;
import com.service.ValidatorService;
import com.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    public AcademicYearRegistrationController(AcademicYearRegistrationService academicYearRegistrationService,
                                              StudentService studentService, ValidatorService validatorService) {
        this.academicYearRegistrationService = academicYearRegistrationService;
        this.studentService = studentService;
        this.validatorService = validatorService;
    }

    @PostMapping(path = "/student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submitStudentInfoForm(@RequestBody StudentInfoForm form) {
        if (!validatorService.checkStudentInfoForm(form)) {
            return new ResponseEntity<>(MessageUtil.INVALID_FORM, HttpStatus.BAD_REQUEST);
        }

        return academicYearRegistrationService.submitStudentRegistration(form);
    }

    @PostMapping(path = "/academic-year", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submitNewAcademicYearForm(@RequestBody NewAcademicYearForm form) {
        return academicYearRegistrationService.submitAcademicYearRegistration(form);
    }

    @PostMapping(path = "/student-choice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submitStudentChoice(@RequestBody StudentChoiceForm form) {
        return academicYearRegistrationService.saveStudentChoice(form);
    }
}
