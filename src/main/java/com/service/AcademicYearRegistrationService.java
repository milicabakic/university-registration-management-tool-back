package com.service;

import com.mapper.ObjectMapper;
import com.model.*;
import com.repository.AcademicYearRegistrationRepository;
import com.request.NewAcademicYearForm;
import com.request.StudentChoiceForm;
import com.request.StudentInfoForm;
import com.response.NewAcademicYearResponse;
import com.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AcademicYearRegistrationService {

    private AcademicYearRegistrationRepository academicYearRegistrationRepository;
    private StudentService studentService;
    private AcademicProgramService academicProgramService;
    private SubjectService subjectService;
    private GroupOfSubjectsService groupOfSubjectsService;
    private ObjectMapper objectMapper;

    @Autowired
    public AcademicYearRegistrationService(AcademicYearRegistrationRepository academicYearRegistrationRepository, ObjectMapper objectMapper,
                                           StudentService studentService, AcademicProgramService academicProgramService,
                                           SubjectService subjectService, GroupOfSubjectsService groupOfSubjectsService) {
        this.academicYearRegistrationRepository = academicYearRegistrationRepository;
        this.studentService = studentService;
        this.academicProgramService = academicProgramService;
        this.subjectService = subjectService;
        this.groupOfSubjectsService = groupOfSubjectsService;
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<?> submitStudentRegistration(StudentInfoForm form) {
        Student student = studentService.findByFullName(form.getFullName());
        boolean renewedYear = form.isRenewedYear();
        AcademicProgram academicProgram = academicProgramService.findByAcademicProgramCode(form.getChosenAcademicProgramCode());

        if(student == null || academicProgram == null)
            return new ResponseEntity<>(MessageUtil.WRONG_CREDENTIALS, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(saveAcademicYearRegistration(student, renewedYear, academicProgram, form.getNextAcademicYear()),
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


    public boolean updateAcademicYearRegistration(Long id, List<Subject> subjects,
                                                  GroupOfSubjects groupOdd, GroupOfSubjects groupEven) {
        Optional<AcademicYearRegistration> registration = academicYearRegistrationRepository.findById(id);
        if(registration.isPresent()) {
            return false;
        }
        registration.get().setSubjects(subjects);
        registration.get().setGroupOfSubjectsOdd(groupOdd);
        registration.get().setGroupOfSubjectsEven(groupEven);
        academicYearRegistrationRepository.save(registration.get());
        return true;
    }


    public ResponseEntity<?> submitAcademicYearRegistration(NewAcademicYearForm form) {
        List<Subject> subjects;
        if(form.isRenewed()) {
            subjects = subjectService.findSubjectsByAcademicYearLess(form.getAcademicYear(), true);
        }
        else {
            subjects = subjectService.findSubjectsByAcademicYearLess(form.getAcademicYear(), false);
        }

        List<GroupOfSubjects> groups = new ArrayList<>();
        if(!form.isRenewed() && form.getAcademicYear() >= 3) {
            groups = groupOfSubjectsService.findGroupsByAcademicYear(form.getAcademicYear(), form.getAcademicProgramCode());
        }

        NewAcademicYearResponse response = new NewAcademicYearResponse(objectMapper.convertSubjectsListToDto(subjects),
                objectMapper.convertGroupOfSubjectsListToDto(groups));

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    public ResponseEntity<?> saveStudentChoice(StudentChoiceForm form) {
        List<Subject> subjects = subjectService.findAllById(form.getOldSubjects());
        GroupOfSubjects groupOdd = groupOfSubjectsService.findById(form.getGroupOddId());
        GroupOfSubjects groupEven = groupOfSubjectsService.findById(form.getGroupEvenId());

        if(!updateAcademicYearRegistration(form.getRegistrationId(), subjects, groupOdd, groupEven)) {
            return new ResponseEntity<>(MessageUtil.REGISTRATION_REJECTED, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(MessageUtil.REGISTRATION_ACCEPTED, HttpStatus.ACCEPTED);
    }

}
