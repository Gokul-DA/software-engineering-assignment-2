package org.example.db;

import org.example.AdminHP;
import org.example.OwnerHP;
import org.example.VeterinarianHP;
import org.example.models.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    public static Connection connection = null;

    public static void establishConnection() {
        String url_SQLServer = "jdbc:sqlserver://localhost:1433;database=software_engineering;user=common;password=common";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url_SQLServer);
        } catch (NullPointerException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static String hashPassword(String userPassword) {
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(userPassword.getBytes(), 0, userPassword.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void authenticateOwnerInfo(int userID, String userPassword) {
        String hashedUserPassword = hashPassword(userPassword);
        String queryDetails = "select * from petstore.owner where ownerId = " + userID;
        Statement stmt;
        OwnerUser ownerUser = new OwnerUser();
        try {
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(queryDetails);
            while (rs.next()) {
                ownerUser.setOwnerId(rs.getInt("ownerId"));
                ownerUser.setOwnerName(rs.getString("ownerName"));
                ownerUser.setOwnerPassword(rs.getString("ownerPassword"));
                ownerUser.setOwnerAddress(rs.getString("ownerAddress"));
                ownerUser.setOwnerEmail(rs.getString("emailAddress"));
                ownerUser.setOwnerMobileNumber(rs.getString("mobileNumber"));
            }
            ownerUser.setPetDetailsList(updatePetDetails(ownerUser.getOwnerId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!hashedUserPassword.equals(ownerUser.getOwnerPassword())) {
            System.out.println("Incorrect Password Try again!!!!");
            System.exit(0);
        }
        OwnerHP ownerHP = new OwnerHP();
        ownerHP.showHomePage(ownerUser);
    }

    public static void authenticateVetInfo(int userID, String userPassword) {
        String hashedUserPassword = hashPassword(userPassword);
        String queryDetails = "select * from petstore.veterenarian where vetId = " + userID;
        Statement stmt;
        VeterinarianUser veterinarianUser = new VeterinarianUser();
        try {
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(queryDetails);
            while (rs.next()) {
                veterinarianUser.setVetId(rs.getInt("vetId"));
                veterinarianUser.setVetName(rs.getString("vetName"));
                veterinarianUser.setVetPassword(rs.getString("vetPassword"));
                veterinarianUser.setVetAddress(rs.getString("vetAddress"));
                veterinarianUser.setVetEmail(rs.getString("vetEmail"));
                veterinarianUser.setVerMobileNumber(rs.getString("verMobileNumber"));
                veterinarianUser.setVetSpeciality(rs.getString("vetSpeciality"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!hashedUserPassword.equals(veterinarianUser.getVetPassword())) {
            System.out.println("Incorrect Password Try again!!!!");
            System.exit(0);
        }
        VeterinarianHP ownerHP = new VeterinarianHP();
        ownerHP.showHomePage(veterinarianUser);
    }

    public static void authenticateAdminInfo(int userID, String userPassword) {
        String hashedUserPassword = hashPassword(userPassword);
        String queryDetails = "select * from petstore.admin where adminID = " + userID;
        Statement stmt;
        AdminUser adminUser = new AdminUser();
        try {
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(queryDetails);
            while (rs.next()) {
                adminUser.setAdminID(rs.getInt("adminID"));
                adminUser.setAdminName(rs.getString("adminName"));
                adminUser.setAdminPassword(rs.getString("adminPassword"));
                adminUser.setAdminAddress(rs.getString("adminAddress"));
                adminUser.setEmailAddress(rs.getString("emailAddress"));
                adminUser.setEmailAddress(rs.getString("mobileNumber"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!hashedUserPassword.equals(adminUser.getAdminPassword())) {
            System.out.println("Incorrect Password Try again!!!!");
            System.exit(0);
        }
        AdminHP adminHP = new AdminHP();
        adminHP.showHomePage(adminUser);
    }


    public static List<PetDetails> updatePetDetails(int ownerid) {
        List<PetDetails> petDetailsList = new ArrayList<>();
        PetDetails petDetails;
        String query = "Select * from petstore.pet where ownerId = " + ownerid;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                petDetails = new PetDetails();
                petDetails.setPetId(rs.getInt(1));
                petDetails.setPetName(rs.getString(2));
                petDetails.setPetType(rs.getString(3));
                petDetails.setGender(rs.getString(4));
                petDetails.setAge(rs.getInt(5));
                petDetailsList.add(petDetails);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return petDetailsList;
    }

    public static List<Appointment> getAllAppointments(int vetId) {
        List<Appointment> appointmentList = new ArrayList<>();
        Appointment appointment;
        String query = "Select * from petstore.appointment where apptStatus !='COMPLETED'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                appointment = new Appointment();
                appointment.setApptId(rs.getInt(1));
                appointment.setApptDat(rs.getString(2));
                appointment.setApptTime(rs.getString(3));
                appointment.setApptStatus(rs.getString(4));
                appointment.setPetId(rs.getInt(5));
                appointment.setOwnerId(rs.getInt(6));
                appointmentList.add(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }
}
