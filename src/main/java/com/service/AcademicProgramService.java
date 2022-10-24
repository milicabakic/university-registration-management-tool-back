package com.service;

import com.mapper.ObjectMapper;
import com.model.AcademicProgram;
import com.repository.AcademicProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicProgramService {

    private AcademicProgramRepository academicProgramRepository;
    private ObjectMapper mapper;


    @Autowired
    public AcademicProgramService(AcademicProgramRepository academicProgramRepository, ObjectMapper mapper) {
        this.academicProgramRepository = academicProgramRepository;
        this.mapper = mapper;
    }

    public ResponseEntity<?> findAll() {
        List<AcademicProgram> academicPrograms = academicProgramRepository.findAll();
        return new ResponseEntity(mapper.convertAcademicProgramListToDto(academicPrograms), HttpStatus.ACCEPTED);
    }

    public AcademicProgram findByAcademicProgramCode(String academicProgramCode) {
        return academicProgramRepository.findByCode(academicProgramCode);
    }

    public AcademicProgram saveAcademicProgram(AcademicProgram academicProgram) {
        return academicProgramRepository.save(academicProgram);
    }
}
