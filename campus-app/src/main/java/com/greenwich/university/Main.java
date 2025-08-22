package com.greenwich.university;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 7) {
            System.out.println("\n=== Campus Service System Menu ===");
            System.out.println("1. Course Registration");
            System.out.println("2. Digital Library System");
            System.out.println("3. File Tracker / Assignment Planner");
            System.out.println("4. Room Booking System");
            System.out.println("5. Campus Event Calendar");
            System.out.println("6. Print Job Manager");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Launching Course Registration...");
                    // TODO: call CourseRegistration module methods
                    break;
                case 2:
                    System.out.println("Launching Digital Library System...");
                    // TODO: call DigitalLibrarySystem methods
                    break;
                case 3:
                    System.out.println("Launching File Tracker / Assignment Planner...");
                    // TODO: call FileTracker methods
                    break;
                case 4:
                    System.out.println("Launching Room Booking System...");
                    // TODO: call RoomBookingSystem methods
                    break;
                case 5:
                    System.out.println("Launching Campus Event Calendar...");
                    // TODO: call CampusEventCalendar methods
                    break;
                case 6:
                    System.out.println("Launching Print Job Manager...");
                    // TODO: call PrintJobManager methods
                    break;
                case 7:
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }

        scanner.close();
    }
}
