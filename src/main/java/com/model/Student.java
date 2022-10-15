package com.model;

import com.model.value_object.Index;
import com.model.value_object.Profile;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String middleName;
    private String surname;
    private String JMBG;

    @Embedded
    private Index index;

    @Embedded
    private Profile profile;

    @OneToMany
    private List<AcademicYearRegistration> academicYearRegistrations;
}
