package com.service;

import com.mapper.ObjectMapper;
import com.model.Student;
import com.repository.StudentRepository;
import com.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private ObjectMapper mapper;

    private static final String WHITESPACE = " ";


    @Autowired
    public StudentService(StudentRepository studentRepository, ObjectMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    public ResponseEntity<?> login(String username) {
        Student student = findByUsername(username);
        if (student == null) {
            return new ResponseEntity(MessageUtil.WRONG_CREDENTIALS, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(mapper.convertStudentToDto(student), HttpStatus.ACCEPTED);
    }

    public Student findByUsername(String username) {
        return studentRepository.findByProfileUsername(username);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findByFullName(String fullName) {
        String data[] = fullName.split(WHITESPACE);
        StringBuilder name = new StringBuilder();
        String surname = data[data.length - 1];

        for (int i = 0; i < data.length - 1; i++) {
            name.append(data[i]);
            name.append(WHITESPACE);
        }

        return studentRepository.findByNameAndSurname(name.toString().trim(), surname);
    }

    public boolean isUsernameInValidFormat(String username) {
        return true;
    }
}
