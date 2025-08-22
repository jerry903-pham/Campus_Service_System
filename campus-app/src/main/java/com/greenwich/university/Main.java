package com.greenwich.university.menu;

import java.util.Scanner;

// Import your modules here
// Example:
// import com.greenwich.university.courseregistration.CourseRegistration;
// import com.greenwich.university.roombookingsystem.RoomBookingSystem;
// import com.greenwich.university.digitallibrary.DigitalLibrarySystem;

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
                case 1:
                    launchCourseRegistration();
                    break;
                case 2:
                    launchDigitalLibrarySystem();
                    break;
                case 3:
                    launchRoomBookingSystem();
                    break;
                case 4:
                    launchAssignmentPlanner();
                    break;
                case 5:
                    launchCampusEventCalendar();
                    break;
                case 6:
                    launchPrintJobManager();
                    break;
                case 0:
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void launchCourseRegistration() {
        System.out.println("Launching Course Registration...");
        // TODO: Call your CourseRegistration main class or methods
        // Example:
        // CourseRegistration.main(new String[]{});
    }

    private static void launchDigitalLibrarySystem() {
        System.out.println("Launching Digital Library System...");
        // TODO: Call DigitalLibrarySystem methods
    }

    private static void launchRoomBookingSystem() {
        System.out.println("Launching Room Booking System...");
        // TODO: Call RoomBookingSystem methods
    }

    private static void launchAssignmentPlanner() {
        System.out.println("Launching Assignment Planner...");
        // TODO: Call AssignmentPlanner methods
    }

    private static void launchCampusEventCalendar() {
        System.out.println("Launching Campus Event Calendar...");
        // TODO: Call CampusEventCalendar methods
    }

    private static void launchPrintJobManager() {
        System.out.println("Launching Print Job Manager...");
        // TODO: Call PrintJobManager methods
    }

    public static void main(String[] args) {
        showMenu();
    }
}
