package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class GroupOfSubjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int semester;

    @JsonIgnore
    @ManyToOne
    private AcademicProgram academicProgram;

    @ManyToMany
    private List<Subject> subjects;
}
