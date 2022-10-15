package com.service;

import com.model.AcademicProgram;
import com.model.AcademicYearRegistration;
import com.model.Student;
import com.repository.AcademicYearRegistrationRepository;
import com.request.StudentInfoForm;
import com.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AcademicYearRegistrationService {

    private AcademicYearRegistrationRepository academicYearRegistrationRepository;
    private StudentService studentService;
    private AcademicProgramService academicProgramService;


    @Autowired
    public AcademicYearRegistrationService(AcademicYearRegistrationRepository academicYearRegistrationRepository,
                                           StudentService studentService, AcademicProgramService academicProgramService) {
        this.academicYearRegistrationRepository = academicYearRegistrationRepository;
        this.studentService = studentService;
        this.academicProgramService = academicProgramService;
    }

    public ResponseEntity<?> submitAcademicYearRegistration(StudentInfoForm studentInfoForm) {
        Student student = studentService.findByFullName(studentInfoForm.getFullName());
        boolean renewedYear = studentInfoForm.isRenewedYear();
        AcademicProgram academicProgram = academicProgramService.findByAcademicProgramCode(studentInfoForm.getChosenAcademicProgramCode());

        if(student == null || academicProgram == null)
            return new ResponseEntity<>(MessageUtil.WRONG_CREDENTIALS, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(saveAcademicYearRegistration(student, renewedYear, academicProgram, studentInfoForm.getNextAcademicYear()),
                HttpStatus.ACCEPTED);
    }

    public AcademicYearRegistration saveAcademicYearRegistration(Student student, boolean renewedYear,
                                                                 AcademicProgram academicProgram, int academicYear) {
        AcademicYearRegistration academicYearRegistration = new AcademicYearRegistration();
        academicYearRegistration.setStudent(student);
        academicYearRegistration.setRenewed(renewedYear);
        academicYearRegistration.setCurrAcademicProgram(academicProgram);
        academicYearRegistration.setAcademicYear(academicYear);
        academicYearRegistration = academicYearRegistrationRepository.save(academicYearRegistration);

        student.getAcademicYearRegistrations().add(academicYearRegistration);
        studentService.saveStudent(student);
        return academicYearRegistration;
    }

}
