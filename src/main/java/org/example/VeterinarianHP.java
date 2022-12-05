package org.example;

import org.example.models.Appointment;
import org.example.models.VeterinarianUser;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.example.Main.scanner;
import static org.example.db.DB.connection;
import static org.example.db.DB.getAllAppointments;

public class VeterinarianHP {

    public void showHomePage(VeterinarianUser veterinarianUser) {

        System.out.println("***** welcome, Doc." + veterinarianUser.getVetName());
        int userChoice=0;
        do {
            System.out.println("\nChoose a action:");
            System.out.println("1.Displaying all pending Appointments\n2.Update a appointment\n3.Logout");

            List<Appointment> appointmentList;
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    appointmentList = getAllAppointments(veterinarianUser.getVetId());
                    for (Appointment appointment : appointmentList) {
                        System.out.println(appointment);
                    }
                    break;
                case 2:
                    appointmentList = getAllAppointments(veterinarianUser.getVetId());
                    System.out.println("Which Appointment do you want to update");
                    for (Appointment appointment : appointmentList) {
                        System.out.println("Appt#" + appointment.getApptId() + " ApptDate: " + appointment.getApptDat() + " ApptTime: " + appointment.getApptTime());
                    }
                    int apptToUpdate = scanner.nextInt();
                    System.out.println("What status do u want to update the appointment to??");
                    System.out.println("1.Completed\n2.Cancelled\n3.Reschedule");
                    int statusChoice = scanner.nextInt();
                    String status = setStatus(statusChoice);
                    String query = "update petstore.appointment set apptStatus = ? where apptId = ? ";
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, status);
                        preparedStatement.setInt(2, apptToUpdate);
                        int i = preparedStatement.executeUpdate();
                        if (i > 0)
                            System.out.println("Appointment updated Successfully");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
            break;
            case 3:
                System.out.println("Logged Out Successfully");
                System.exit(0);
                break;
            default:
        }
        }while(userChoice!=3);

    }

    private String setStatus(int statusChoice) {
        if (statusChoice == 1)
            return "COMPLETED";
        else if (statusChoice == 2)
            return "CANCELLED";
        else if (statusChoice == 3)
            return "RESCHEDULE";
        return "";
    }
}
