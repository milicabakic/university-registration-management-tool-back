package com.controller;

import com.request.LoginRequest;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthenticationController {

    private StudentService studentService;


    @Autowired
    public AuthenticationController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if(!studentService.isUsernameInValidFormat(loginRequest.getUsername())) {
            return ResponseEntity.status(404).build();
        }

        return studentService.login(loginRequest.getUsername());
    }

}
