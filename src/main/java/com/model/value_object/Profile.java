package com.model.value_object;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Profile {

    private String username;
    private String password;

}
