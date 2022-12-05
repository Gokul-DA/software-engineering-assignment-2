package org.example;

import org.example.db.DB;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DB.establishConnection();

        int loginChoice = 0;
        int userID = 0;
        String userPassword = "";
        System.out.println("Welcome to PetzStore clinic");
        System.out.println("Choose the type of Login");
        System.out.println("1.Pet-owner\t2.Veterinarian\t3.Admin");
        loginChoice =Integer.parseInt(scanner.nextLine());

            switch (loginChoice) {
                case 1:
                    System.out.println("*********Pet Owner Login*********");
                    System.out.println("Enter you owner id");
                    userID = scanner.nextInt();
                    System.out.println("Enter you password");
                    userPassword = scanner.next();
                    DB.authenticateOwnerInfo(userID,userPassword);
                    break;
                case 2:
                    System.out.println("*********Veterinarian Login*********");
                    System.out.println("Enter you vet id");
                    userID = scanner.nextInt();
                    System.out.println("Enter you password");
                    userPassword = scanner.next();
                    DB.authenticateVetInfo(userID,userPassword);
                    break;
                case 3:
                    System.out.println("*********Admin Login*********");
                    System.out.println("Enter you admin id");
                    userID = scanner.nextInt();
                    System.out.println("Enter you password");
                    userPassword = scanner.next();
                    DB.authenticateAdminInfo(userID,userPassword);
                    break;
                default:
                    System.out.println("********* Incorrect Option. Try again *********");

        }
    }
}