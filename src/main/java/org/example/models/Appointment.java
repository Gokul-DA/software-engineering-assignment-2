package org.example.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Appointment {

    private int apptId;
    private String apptDat;
    private String apptTime;
    private String apptStatus;
    private int petId;
    private int ownerId;

}
