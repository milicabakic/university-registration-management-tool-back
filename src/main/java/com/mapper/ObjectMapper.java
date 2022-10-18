package com.mapper;

import com.dto.GroupOfSubjectsDto;
import com.dto.SubjectDto;
import com.model.GroupOfSubjects;
import com.model.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapper {

    private ModelMapper modelMapper;


    @Autowired
    public ObjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public List<SubjectDto> convertSubjectsListToDto(List<Subject> subjects) {
        return subjects.stream()
                .map(this::convertSubjectToDto)
                .collect(Collectors.toList());
    }

    public List<GroupOfSubjectsDto> convertGroupOfSubjectsListToDto(List<GroupOfSubjects> groups) {
        return groups.stream()
                .map(this::convertGroupOfSubjectsToDto)
                .collect(Collectors.toList());
    }

    private SubjectDto convertSubjectToDto(Subject subject) {
        SubjectDto subjectDto = modelMapper.map(subject, SubjectDto.class);
        return subjectDto;
    }

    private GroupOfSubjectsDto convertGroupOfSubjectsToDto(GroupOfSubjects group) {
        GroupOfSubjectsDto groupDto = modelMapper.map(group, GroupOfSubjectsDto.class);
        groupDto.setSubjects(convertSubjectsListToDto(group.getSubjects()));
        return groupDto;
    }

}
