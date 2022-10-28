package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class AcademicYearRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean renewed;
    private int academicYear;

    @JsonIgnore
    @ManyToOne
    private AcademicProgram currAcademicProgram;

    @JsonIgnore
    @ManyToOne
    private GroupOfSubjects groupOfSubjectsOdd;

    @JsonIgnore
    @ManyToOne
    private GroupOfSubjects groupOfSubjectsEven;

    @JsonIgnore
    @ManyToOne
    private Student student;

    @ManyToMany
    private List<Subject> subjects;
}
