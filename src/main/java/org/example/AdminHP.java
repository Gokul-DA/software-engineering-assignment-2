package org.example;

import org.example.models.AdminUser;
import org.example.models.OwnerUser;
import org.example.models.PetDetails;
import org.example.models.VeterinarianUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.example.Main.scanner;
import static org.example.db.DB.connection;

public class AdminHP {
    public void showHomePage(AdminUser adminUser) {
        System.out.println("***** welcome, Admin " + adminUser.getAdminName());

        int userChoice = 0;
        do {
            System.out.println("Choose a action");
            System.out.println("\n1. View all Owner Details\n2. View all Pet Details\n3. View all Vet Details\n4. Logout");
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    displayAllOwnerDetails();
                    break;
                case 2:
                    displayAllPetDetails();
                    break;
                case 3:
                    displayAllVetDetails();
                    break;
                case 4:
                    System.out.println("Logged out Successfully");
                    System.exit(0);
                    break;
                default:
            }
        } while (userChoice != 4);
    }

    private void displayAllVetDetails() {
        List<VeterinarianUser> list = new ArrayList<>();
        VeterinarianUser veterinarianUser;
        String query = "select * from petstore.veterenarian";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println("All Vet Details");
                veterinarianUser = new VeterinarianUser();
                veterinarianUser.setVetId(rs.getInt("vetId"));
                veterinarianUser.setVetName(rs.getString("vetName"));
                veterinarianUser.setVetPassword(rs.getString("vetPassword"));
                veterinarianUser.setVetAddress(rs.getString("vetAddress"));
                veterinarianUser.setVetEmail(rs.getString("vetEmail"));
                veterinarianUser.setVerMobileNumber(rs.getString("verMobileNumber"));
                veterinarianUser.setVetSpeciality(rs.getString("vetSpeciality"));
                System.out.println(veterinarianUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayAllPetDetails() {
        List<PetDetails> list = new ArrayList<>();
        PetDetails petDetails;
        String query = "select * from petstore.pet";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            System.out.println("All Pet Details");
            while (rs.next()) {
                petDetails = new PetDetails();
                petDetails.setPetId(rs.getInt(1));
                petDetails.setPetName(rs.getString(2));
                petDetails.setPetType(rs.getString(3));
                petDetails.setGender(rs.getString(4));
                petDetails.setAge(rs.getInt(5));
                System.out.println(petDetails);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayAllOwnerDetails() {
        List<OwnerUser> list = new ArrayList<>();
        OwnerUser ownerUser;
        String query = "select * from petstore.owner";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            System.out.println("All Owner Details");
            while (rs.next()) {
                ownerUser = new OwnerUser();
                ownerUser.setOwnerId(rs.getInt("ownerId"));
                ownerUser.setOwnerName(rs.getString("ownerName"));
                ownerUser.setOwnerPassword(rs.getString("ownerPassword"));
                ownerUser.setOwnerAddress(rs.getString("ownerAddress"));
                ownerUser.setOwnerEmail(rs.getString("emailAddress"));
                ownerUser.setOwnerMobileNumber(rs.getString("mobileNumber"));
                System.out.println(ownerUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
