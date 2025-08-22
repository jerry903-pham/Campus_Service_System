package src;//import model.Course;
//import model.Student;
//import service.CourseManager;
//import service.RegistrationSystem;
//import util.InputValidator;
//
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        InputValidator iv = new InputValidator(scanner);
//        CourseManager cm = new CourseManager();
//        RegistrationSystem rs = new RegistrationSystem(cm);
//
//        boolean running = true;
//        while (running) {
//            System.out.println("\n=== Course Registration System ===");
//            System.out.println("1. Add Course");
//            System.out.println("2. Add Student");
//            System.out.println("3. Register Student for Course");
//            System.out.println("4. Drop Student from Course");
//            System.out.println("5. View Student Schedule");
//            System.out.println("6. Search Course by Code");
//            System.out.println("7. Search Course by Name Fragment");
//            System.out.println("8. Display All Courses");
//            System.out.println("9. Display All Students");
//            System.out.println("0. Exit");
//
//            int choice = iv.getInt("Choose an option: ", 0, 9);
//
//            switch (choice) {
//                case 1: {
//                    String code = iv.getNonEmptyString("Course code: ");
//                    String name = iv.getNonEmptyString("Course name: ");
//                    int cap = iv.getInt("Capacity: ", 1, 1000);
//                    Course c = new Course(code, name, cap);
//                    if (cm.addCourse(c)) {
//                        System.out.println("Added: " + c);
//                    } else {
//                        System.out.println("Course already exists or invalid.");
//                    }
//                    break;
//                }
//                case 2: {
//                    String id = iv.getNonEmptyString("Student ID: ");
//                    String name = iv.getNonEmptyString("Student name: ");
//                    Student s = new Student(id, name);
//                    if (rs.addStudent(s)) {
//                        System.out.println("Added: " + s);
//                    } else {
//                        System.out.println("Student already exists or invalid.");
//                    }
//                    break;
//                }
//                case 3: {
//                    String sid = iv.getNonEmptyString("Student ID: ");
//                    String code = iv.getNonEmptyString("Course code: ");
//                    System.out.println(rs.register(sid, code));
//                    break;
//                }
//                case 4: {
//                    String sid = iv.getNonEmptyString("Student ID: ");
//                    String code = iv.getNonEmptyString("Course code: ");
//                    System.out.println(rs.drop(sid, code));
//                    break;
//                }
//                case 5: {
//                    String sid = iv.getNonEmptyString("Student ID: ");
//                    System.out.println(rs.viewStudentSchedule(sid));
//                    break;
//                }
//                case 6: {
//                    String code = iv.getNonEmptyString("Course code: ");
//                    System.out.println(rs.searchCourseByCode(code));
//                    break;
//                }
//                case 7: {
//                    String frag = iv.getNonEmptyString("Enter part of course name: ");
//                    var results = rs.searchCourseByName(frag);
//                    if (results.size() == 0) {
//                        System.out.println("No courses found.");
//                    } else {
//                        for (int i = 0; i < results.size(); i++) {
//                            System.out.println(results.get(i));
//                        }
//                    }
//                    break;
//                }
//                case 8: {
//                    cm.displayAllCourses();
//                    break;
//                }
//                case 9: {
//                    rs.displayAllStudents();
//                    break;
//                }
//                case 0:
//                    running = false;
//                    System.out.println("Goodbye!");
//                    break;
//            }
//        }
//    }
//}
//
import model.Course;
import model.Student;
import service.CourseManager;
import service.RegistrationSystem;
import util.InputValidator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputValidator iv = new InputValidator(scanner);
        CourseManager cm = new CourseManager();
        RegistrationSystem rs = new RegistrationSystem(cm);

        boolean running = true;
        while (running) {
            System.out.println("\n=== Course Registration System ===");
            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.println("0. Exit");

            int role = iv.getInt("Choose a role: ", 0, 2);

            switch (role) {
                case 1 -> adminMenu(iv, cm, rs);
                case 2 -> studentMenu(iv, cm, rs);
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
            }
        }
    }

    // =================== ADMIN MENU ===================
    private static void adminMenu(InputValidator iv, CourseManager cm, RegistrationSystem rs) {
        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Course");
            System.out.println("2. Remove Course");
            System.out.println("3. Display All Courses");
            System.out.println("4. Display All Students");
            System.out.println("0. Back");

            int choice = iv.getInt("Choose an option: ", 0, 4);

            switch (choice) {
                case 1 -> {
                    String code = iv.getNonEmptyString("Course code: ");
                    String name = iv.getNonEmptyString("Course name: ");
                    int cap = iv.getInt("Capacity: ", 1, 1000);
                    Course c = new Course(code, name, cap);
                    if (cm.addCourse(c)) {
                        System.out.println("Added: " + c);
                    } else {
                        System.out.println("Course already exists or invalid.");
                    }
                }
                case 2 -> {
                    String code = iv.getNonEmptyString("Course code to remove: ");
                    if (cm.removeCourse(code)) {
                        System.out.println("Removed course: " + code);
                    } else {
                        System.out.println("No such course found.");
                    }
                }
                case 3 -> cm.displayAllCourses();
                case 4 -> rs.displayAllStudents();
                case 0 -> adminRunning = false;
            }
        }
    }

    // =================== STUDENT MENU ===================
    private static void studentMenu(InputValidator iv, CourseManager cm, RegistrationSystem rs) {
        boolean studentRunning = true;
        while (studentRunning) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View My Schedule");
            System.out.println("5. Search Course by Code");
            System.out.println("6. Search Course by Name Fragment");
            System.out.println("0. Back");

            int choice = iv.getInt("Choose an option: ", 0, 6);

            switch (choice) {
                case 1 -> {
                    String id = iv.getNonEmptyString("Student ID: ");
                    String name = iv.getNonEmptyString("Student name: ");
                    Student s = new Student(id, name);
                    if (rs.addStudent(s)) {
                        System.out.println("Added: " + s);
                    } else {
                        System.out.println("Student already exists or invalid.");
                    }
                }
                case 2 -> {
                    String sid = iv.getNonEmptyString("Student ID: ");
                    String code = iv.getNonEmptyString("Course code: ");
                    System.out.println(rs.register(sid, code));
                }
                case 3 -> {
                    String sid = iv.getNonEmptyString("Student ID: ");
                    String code = iv.getNonEmptyString("Course code: ");
                    System.out.println(rs.drop(sid, code));
                }
                case 4 -> {
                    String sid = iv.getNonEmptyString("Student ID: ");
                    System.out.println(rs.viewStudentSchedule(sid));
                }
                case 5 -> {
                    String code = iv.getNonEmptyString("Course code: ");
                    System.out.println(rs.searchCourseByCode(code));
                }
                case 6 -> {
                    String frag = iv.getNonEmptyString("Enter part of course name: ");
                    var results = rs.searchCourseByName(frag);
                    if (results.size() == 0) {
                        System.out.println("No courses found.");
                    } else {
                        for (int i = 0; i < results.size(); i++) {
                            System.out.println(results.get(i));
                        }
                    }
                }
                case 0 -> studentRunning = false;
            }
        }
    }
}

