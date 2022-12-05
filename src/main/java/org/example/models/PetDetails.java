package org.example.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PetDetails {

    private int petId;
    private String petName;
    private String petType;
    private String gender;
    private int age;
}
