package com.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupOfSubjectsDto {

    private Long id;
    private String name;
    private List<SubjectDto> subjects;

}
