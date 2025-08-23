package com.greenwich.university;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🎓 Welcome to Greenwich University Campus System!");

        while (true) {
            displayMainMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        runPrintJobManager();
                        break;
                    case 2:
                        runCourseRegistration();
                        break;
                    case 3:
                        System.out.println("👥 Student Management System - Coming Soon!");
                        break;
                    case 4:
                        System.out.println("👋 Thank you for using Greenwich University Campus System!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("❌ Invalid option! Please select 1-4.");
                }

                System.out.println("\nPress Enter to return to main menu...");
                scanner.nextLine();

            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number!");
            } catch (Exception e) {
                System.err.println("❌ Error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("       GREENWICH UNIVERSITY CAMPUS SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1. 🖨️  Print Job Manager");
        System.out.println("2. 📚 Course Registration");
        System.out.println("3. 👥 Student Management (Coming Soon)");
        System.out.println("4. 🚪 Exit");
        System.out.println("=".repeat(50));
        System.out.print("Select option (1-4): ");
    }

    private static void runPrintJobManager() {
        try {
            System.out.println("\n🖨️ Starting Print Job Manager...");
            PrintJobManagerAPI printAPI = new PrintJobManagerAPI();
            printAPI.start();
        } catch (Exception e) {
            System.err.println("❌ Error running Print Job Manager: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void runCourseRegistration() {
        try {
            System.out.println("\n📚 Starting Course Registration...");
            CourseRegistrationAPI courseAPI = new CourseRegistrationAPI();
            courseAPI.start();
        } catch (Exception e) {
            System.err.println("❌ Error running Course Registration: " + e.getMessage());
            e.printStackTrace();
        }
    }
}