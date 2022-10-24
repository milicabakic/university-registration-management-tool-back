package com.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class AcademicProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
