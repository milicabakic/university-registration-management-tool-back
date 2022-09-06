package com.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class GroupOfSubjects {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int semester;

    @ManyToOne
    private AcademicProgram academicProgram;

    @ManyToMany
    private List<Subject> subjects;
}
