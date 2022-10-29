package com.service;

import com.model.AcademicProgram;
import com.model.Subject;
import com.repository.SubjectRepository;
import com.request.SubjectForm;
import com.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;
    private AcademicProgramService academicProgramService;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, AcademicProgramService academicProgramService) {
        this.subjectRepository = subjectRepository;
        this.academicProgramService = academicProgramService;
    }


    public ResponseEntity<?> submitSubjectForm(SubjectForm subjectForm) {
        AcademicProgram academicProgram = academicProgramService.findByAcademicProgramCode(subjectForm.getAcademicProgramCode());

        if(academicProgram == null)
            return new ResponseEntity(MessageUtil.INVALID_FORM, HttpStatus.BAD_REQUEST);

        return new ResponseEntity(saveSubject(subjectForm, academicProgram), HttpStatus.ACCEPTED);
    }

    public Subject saveSubject(SubjectForm subjectForm, AcademicProgram academicProgram) {
        Subject subject = new Subject();
        subject.setName(subjectForm.getName());
        subject.setCode(subjectForm.getCode());
        subject.setEspb(subjectForm.getEspb());
        subject.setSemester(subjectForm.getSemester());
        subject.setAcademicProgram(academicProgram);
        subject.setAcademicYear(subjectForm.getAcademicYear());
        subject = subjectRepository.save(subject);

        academicProgram.getSubjects().add(subject);
        academicProgramService.saveAcademicProgram(academicProgram);
        return subject;
    }

    public List<Subject> findSubjectsByAcademicYearLess(int academicYear, boolean equals) {
        if (equals) {
            return subjectRepository.findByAcademicYearLessThanEqual(academicYear);
        }

        return subjectRepository.findByAcademicYearLessThan(academicYear);
    }


    public List<Subject> findAllById(List<Long> ids) {
        if (ids.isEmpty()) {
            return null;
        }
        return subjectRepository.findAllById(ids);
    }
}
