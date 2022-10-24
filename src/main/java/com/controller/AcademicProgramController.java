package com.controller;

import com.service.AcademicProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/academic-programs")
@CrossOrigin
public class AcademicProgramController {

    private AcademicProgramService academicProgramService;


    @Autowired
    public AcademicProgramController(AcademicProgramService academicProgramService) {
        this.academicProgramService = academicProgramService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        return academicProgramService.findAll();
    }
}
