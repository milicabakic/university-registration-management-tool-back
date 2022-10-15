package com.model;

import com.model.value_object.Profile;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OfficeEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Embedded
    private Profile profile;

}
