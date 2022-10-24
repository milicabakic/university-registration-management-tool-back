package com.response;

import com.dto.GroupOfSubjectsDto;
import com.dto.SubjectDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NewAcademicYearResponse {

    private List<GroupOfSubjectsDto> groupsOdd;
    private List<GroupOfSubjectsDto> groupsEven;
    private List<SubjectDto> subjectsOdd;
    private List<SubjectDto> subjectsEven;

}
