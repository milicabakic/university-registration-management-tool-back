package com.service;

import com.model.Student;
import com.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student login(String username) {
        return studentRepository.findByProfileUsername(username);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findByFullName(String fullName) {
        String data[] = fullName.split(" ");
        String name = "";
        String surname = data[data.length - 1];

        for(int i = 0; i < data.length - 1; i++) {
            name += data[i];
        }

        return studentRepository.findByNameAndSurname(name, surname);
    }

    public boolean isUsernameInInvalidFormat(String username) {
        return true;
    }
}
