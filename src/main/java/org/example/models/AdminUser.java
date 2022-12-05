package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUser {
    private int adminID;
    private String adminName;
    private String adminPassword;
    private String adminAddress;
    private String emailAddress;
    private String mobileNumber;
}
