package com.response;

import com.dto.GroupOfSubjectsDto;
import com.dto.SubjectDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NewAcademicYearResponse {

    private List<SubjectDto> subjects;
    private List<GroupOfSubjectsDto> groupOfSubjects;

}
