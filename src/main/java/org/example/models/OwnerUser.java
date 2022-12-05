package org.example.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OwnerUser {
    private int ownerId;
    private String ownerName;
    private String ownerPassword;
    private String ownerAddress;
    private String ownerEmail;
    private String ownerMobileNumber;
    List<PetDetails> petDetailsList;
}
