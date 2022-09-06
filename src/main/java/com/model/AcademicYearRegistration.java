package com.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class AcademicYearRegistration {

    @Id
    @GeneratedValue
    private Long id;

    private Boolean renewed;
    private int year;

    @ManyToOne
    private GroupOfSubjects groupOfSubjectsOdd;

    @ManyToOne
    private GroupOfSubjects groupOfSubjectsEven;

    @OneToMany
    private List<Student> students;

    @ManyToMany
    private List<Subject> subjects;
}
