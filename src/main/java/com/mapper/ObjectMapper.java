package com.mapper;

import com.dto.AcademicProgramDto;
import com.dto.AcademicYearRegistrationDto;
import com.dto.GroupOfSubjectsDto;
import com.dto.SubjectDto;
import com.model.AcademicProgram;
import com.model.AcademicYearRegistration;
import com.model.GroupOfSubjects;
import com.model.Subject;
import com.utils.SemesterValue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapper {

    private ModelMapper modelMapper;
    private static final String SEMESTER_ODD = "n";
    private static final String SEMESTER_EVEN = "p";



    @Autowired
    public ObjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public List<SubjectDto> convertSubjectsListToDto(List<Subject> subjects) {
        return subjects.stream()
                .map(this::convertSubjectToDto)
                .collect(Collectors.toList());
    }

    public List<SubjectDto> filterSubjectsEvenToDto(List<Subject> subjects) {
        return subjects.stream()
                .filter(s -> s.getSemester().equals(SemesterValue.parni.toString()))
                .map(this::convertSubjectToDto)
                .collect(Collectors.toList());
    }

    public List<SubjectDto> filterSubjectsOddToDto(List<Subject> subjects) {
        return subjects.stream()
                .filter(s -> s.getSemester().equals(SemesterValue.neparni.toString()))
                .map(this::convertSubjectToDto)
                .collect(Collectors.toList());
    }

    public List<GroupOfSubjectsDto> convertGroupOfSubjectsListToDto(List<GroupOfSubjects> groups) {
        return groups.stream()
                .map(this::convertGroupOfSubjectsToDto)
                .collect(Collectors.toList());
    }

    public List<GroupOfSubjectsDto> filterGroupsEvenToDto(List<GroupOfSubjects> groups) {
        return groups.stream()
                .filter(g -> g.getName().endsWith(SEMESTER_EVEN))
                .map(this::convertGroupOfSubjectsToDto)
                .collect(Collectors.toList());
    }

    public List<GroupOfSubjectsDto> filterGroupsOddToDto(List<GroupOfSubjects> groups) {
        return groups.stream()
                .filter(g -> g.getName().endsWith(SEMESTER_ODD))
                .map(this::convertGroupOfSubjectsToDto)
                .collect(Collectors.toList());
    }

    public SubjectDto convertSubjectToDto(Subject subject) {
        SubjectDto subjectDto = modelMapper.map(subject, SubjectDto.class);
        return subjectDto;
    }

    public GroupOfSubjectsDto convertGroupOfSubjectsToDto(GroupOfSubjects group) {
        GroupOfSubjectsDto groupDto = modelMapper.map(group, GroupOfSubjectsDto.class);
        groupDto.setSubjects(convertSubjectsListToDto(group.getSubjects()));
        return groupDto;
    }

    public AcademicYearRegistrationDto convertAcademicYearRegistrationToDto(AcademicYearRegistration registration) {
        AcademicYearRegistrationDto registrationDto = modelMapper.map(registration, AcademicYearRegistrationDto.class);
        registrationDto.setAcademicProgram(registration.getCurrAcademicProgram().getCode());
        return registrationDto;
    }

    public AcademicProgramDto convertAcademicProgramToDto(AcademicProgram academicProgram) {
        AcademicProgramDto academicProgramDto = modelMapper.map(academicProgram, AcademicProgramDto.class);
        return academicProgramDto;
    }

    public List<AcademicProgramDto> convertAcademicProgramListToDto(List<AcademicProgram> academicPrograms) {
        return academicPrograms.stream()
                .map(this::convertAcademicProgramToDto)
                .collect(Collectors.toList());
    }

}
