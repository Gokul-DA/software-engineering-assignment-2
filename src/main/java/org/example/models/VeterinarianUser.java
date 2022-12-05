package org.example.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VeterinarianUser {

    private int vetId ;
    private String vetName ;
    private String vetPassword ;
    private String vetAddress ;
    private String vetEmail ;
    private String verMobileNumber;
    private String vetSpeciality;
}
