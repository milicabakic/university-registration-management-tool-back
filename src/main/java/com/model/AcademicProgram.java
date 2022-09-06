package com.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class AcademicProgram {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String code;

    @OneToMany
    private List<Subject> subjects;

    @OneToMany
    private List<AcademicYearRegistration> academicYearRegistrations;

    @OneToMany
    private List<GroupOfSubjects> groups;

}
