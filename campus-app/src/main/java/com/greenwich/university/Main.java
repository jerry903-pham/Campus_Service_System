package com.greenwich.university.menu;

import java.util.Scanner;

// Imports for your modules — fill in the correct main class for each module
import com.greenwich.university.CourseRegistration.Main;         // Course Registration
import com.greenwich.university.LibrarySystem;       // Digital Library System
import com.greenwich.university.RoomBookingApp;          // Room Booking System
import com.greenwich.university.LostAndFoundSystem;          // Assignment Planner
import com.greenwich.university.ui.Main            // Print Job Manager

public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("=== Campus Service System ===");
            System.out.println("1. Course Registration");
            System.out.println("2. Digital Library System");
            System.out.println("3. Room Booking System");
            System.out.println("4. Assignment Planner");
            System.out.println("5. Campus Event Calendar");
            System.out.println("6. Print Job Manager");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> launchCourseRegistration();
                case 2 -> launchDigitalLibrarySystem();
                case 3 -> launchRoomBookingSystem();
                case 4 -> launchAssignmentPlanner();
                case 5 -> launchCampusEventCalendar();
                case 6 -> launchPrintJobManager();
                case 0 -> System.out.println("Exiting. Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void launchCourseRegistration() {
        System.out.println("Launching Course Registration...");
        Main.main(new String[]{});  // Call the Main class of CourseRegistration
    }

    private static void launchDigitalLibrarySystem() {
        System.out.println("Launching Digital Library System...");
        Main.main(new String[]{});  // Call the Main class of DigitalLibrarySystem
    }

    private static void launchRoomBookingSystem() {
        System.out.println("Launching Room Booking System...");
        Main.main(new String[]{});  // Call the Main class of RoomBookingSystem
    }

    private static void launchAssignmentPlanner() {
        System.out.println("Launching Assignment Planner...");
        Main.main(new String[]{});  // Call the Main class of AssignmentPlanner
    }

    private static void launchCampusEventCalendar() {
        System.out.println("Launching Campus Event Calendar...");
        Main.main(new String[]{});  // Call the Main class of CampusEventCalendar
    }

    private static void launchPrintJobManager() {
        System.out.println("Launching Print Job Manager...");
        Main.main(new String[]{});  // Call the Main class of PrintJobManager
    }

    public static void main(String[] args) {
        showMenu();
    }
}


