package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
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
