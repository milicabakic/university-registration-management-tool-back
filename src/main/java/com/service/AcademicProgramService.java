package com.service;

import com.model.AcademicProgram;
import com.repository.AcademicProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicProgramService {

    private AcademicProgramRepository academicProgramRepository;


    @Autowired
    public AcademicProgramService(AcademicProgramRepository academicProgramRepository) {
        this.academicProgramRepository = academicProgramRepository;
    }

    public AcademicProgram findByAcademicProgramCode(String academicProgramCode) {
        return academicProgramRepository.findByCode(academicProgramCode);
    }

    public AcademicProgram saveAcademicProgram(AcademicProgram academicProgram) {
        return academicProgramRepository.save(academicProgram);
    }
}
