package com.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
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
