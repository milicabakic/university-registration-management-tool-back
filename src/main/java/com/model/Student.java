package com.model;

import com.model.value_object.Profile;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String middleName;
    private String surname;
    private String JMBG;

    @Embedded
    private Profile profile;

    @OneToMany
    private List<AcademicYearRegistration> academicYearRegistrations;
}
