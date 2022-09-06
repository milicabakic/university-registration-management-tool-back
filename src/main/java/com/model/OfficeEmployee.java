package com.model;

import com.model.value_object.Profile;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class OfficeEmployee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;

    @Embedded
    private Profile profile;

}
