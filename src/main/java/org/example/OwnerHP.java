package org.example;

import org.example.db.DB;
import org.example.models.OwnerUser;
import org.example.models.PetDetails;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.example.Main.scanner;
import static org.example.db.DB.connection;
import static org.example.db.DB.updatePetDetails;

public class OwnerHP {
    public void showHomePage(OwnerUser ownerUser) {
        int userChoice=0;
        System.out.println("Welcome To home page, " + ownerUser.getOwnerName());
        do {
            System.out.println("Choose one of the option below");
            System.out.println("1. Book appointment");
            System.out.println("2. Add pet details");
            System.out.println("3. Logout");
            userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.println("*********Automatic appointment activated*********");
                    PreparedStatement appointmentDetailsInsert = null;
                    String insertAppointmentQuery = "insert into petstore.appointment values('2022-12-12','2:00','BOOKED',?,?)";
                    try {
                        appointmentDetailsInsert = connection.prepareStatement(insertAppointmentQuery);
                        ownerUser.setPetDetailsList(updatePetDetails(ownerUser.getOwnerId()));
                        System.out.println("FOr which pet do you want to see doctor for?");
                        for (PetDetails details : ownerUser.getPetDetailsList()) {
                            System.out.println("Pet# " + details.getPetId() + ": "+details.getPetName());
                        }
                        appointmentDetailsInsert.setInt(1, scanner.nextInt());
                        appointmentDetailsInsert.setInt(2, ownerUser.getOwnerId());

                        int i = appointmentDetailsInsert.executeUpdate();
                        if (i > 0)
                            System.out.println("Appointment Booked Successfully");
                        else
                            System.out.println("Unable to Book appointment. Try after Sometime");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    PreparedStatement petDetailsInsert = null;
                    String insertPetQUery = "insert into petstore.pet values(?,?,?,?,? )";
                    try {
                        petDetailsInsert = connection.prepareStatement(insertPetQUery);

                        System.out.println("Enter your pet name");
                        petDetailsInsert.setString(1, scanner.next());

                        System.out.println("Enter your pet type (Dog,Cat)");
                        petDetailsInsert.setString(2, scanner.next());

                        System.out.println("Enter your pet gender (M,F)");
                        petDetailsInsert.setString(3, scanner.next());

                        System.out.println("Enter your pet age (in numbers)");
                        petDetailsInsert.setInt(4, scanner.nextInt());

                        petDetailsInsert.setInt(5, ownerUser.getOwnerId());

                        int i = petDetailsInsert.executeUpdate();
                        if (i > 0)
                            System.out.println("pet details updated successfully");

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                    break;
                case 3:
                    System.out.println("***Logout Successfully completed***");
                    System.exit(0);
                    break;
                default:
            }
        }while(userChoice!=3);
    }
}
