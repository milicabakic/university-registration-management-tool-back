package com.model.value_object;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Index {

    private String code;
    private String number;
    private int year;

}
