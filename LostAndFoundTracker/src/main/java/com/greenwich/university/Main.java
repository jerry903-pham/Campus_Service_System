package com.greenwich.university;
import java.util.Scanner;

// Entry point of the program
public class Main {
    public static void main(String[] args) {
        LostAndFoundSystem system = new LostAndFoundSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Lost and Found Tracker ---");
            System.out.println("1. Add Item");
            System.out.println("2. View Item");
            System.out.println("3. Claim Item");
            System.out.println("4. Search Item");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    System.out.print("Type (Lost/Found): ");
                    String type = sc.nextLine();
                    system.addItem(desc, date, type);
                    break;
                case 2:
                    system.viewItems();
                    break;
                case 3:
                    System.out.print("Enter Item ID to claim: ");
                    int id = sc.nextInt();
                    system.claimItem(id);
                    break;
                case 4:
                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();
                    system.searchItem(keyword);
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

