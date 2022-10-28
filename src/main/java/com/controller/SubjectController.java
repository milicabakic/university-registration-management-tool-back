package com.controller;

import com.request.SubjectForm;
import com.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin
public class SubjectController {

    private SubjectService subjectService;


    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submitStudentInfoForm(@RequestBody SubjectForm subjectForm) {
        return subjectService.submitSubjectForm(subjectForm);
    }
}
