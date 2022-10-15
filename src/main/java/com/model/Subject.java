package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private int espb;
    private String semester;
    private int academicYear;

    @JsonIgnore
    @ManyToOne
    private AcademicProgram academicProgram;
}
