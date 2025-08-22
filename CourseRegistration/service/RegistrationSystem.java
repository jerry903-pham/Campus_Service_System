package src.service;

import src.datastructures.MyArray;
import src.datastructures.MyHashTable;
import model.Course;
import model.Student;

public class RegistrationSystem {
    private final MyHashTable<String, Student> students;
    private final MyArray<String> studentIds;
    private final CourseManager courseManager;

    public RegistrationSystem(CourseManager courseManager) {
        this.students = new MyHashTable<>();
        this.studentIds = new MyArray<>();
        this.courseManager = courseManager;
    }

    public boolean addStudent(Student s) {
        if (s == null || s.getId() == null) return false;
        String id = normalize(s.getId());
        if (students.get(id) != null) return false;
        students.put(id, s);
        studentIds.add(id);
        return true;
    }

    public Student getStudent(String id) {
        return id == null ? null : students.get(normalize(id));
    }

    public boolean removeStudent(String id) {
        if (id == null) return false;
        String k = normalize(id);
        if (students.remove(k) == null) return false; // assumes remove returns V or null
        for (int i = 0; i < studentIds.size(); i++) {
            String x = studentIds.get(i);
            if (x != null && x.equalsIgnoreCase(k)) {
                studentIds.removeAt(i);
                break;
            }
        }
        return true;
    }

    public String register(String studentId, String courseCode) {
        if (studentId == null || courseCode == null) return "Invalid input.";
        Student stu = students.get(normalize(studentId));
        if (stu == null) return "Student not found.";
        Course c = courseManager.getCourse(normalize(courseCode));
        if (c == null) return "Course not found.";
        if (stu.isEnrolledIn(c.getCode())) return "Already enrolled in " + c.getCode() + ".";

        if (!c.isFull()) {
            if (c.enrollNow(stu.getId())) {
                stu.addCourse(c.getCode());
                return "Enrolled: " + c.getCode() + " - " + c.getName() + ".";
            } else {
                c.enqueueWaitlist(stu.getId());
                return "Course filled during registration. Added to waitlist.";
            }
        } else {
            c.enqueueWaitlist(stu.getId());
            return "Course is full. Added to waitlist for " + c.getCode() + ".";
        }
    }

    public String drop(String studentId, String courseCode) {
        if (studentId == null || courseCode == null) return "Invalid input.";
        Student stu = students.get(normalize(studentId));
        if (stu == null) return "Student not found.";
        Course c = courseManager.getCourse(normalize(courseCode));
        if (c == null) return "Course not found.";
        if (!stu.isEnrolledIn(c.getCode())) return "Not enrolled in " + c.getCode() + ".";

        String promoted = c.dropAndMaybeFillFromWaitlist(stu.getId());
        stu.dropCourse(c.getCode());

        if (promoted != null) {
            Student p = students.get(promoted);
            if (p != null) p.addCourse(c.getCode());
            return "Dropped " + c.getCode() + ". Auto-enrolled from waitlist: " + promoted + ".";
        }
        return "Dropped " + c.getCode() + ".";
    }

    public String searchCourseByCode(String code) {
        if (code == null) return "Invalid input.";
        Course c = courseManager.getCourse(normalize(code));
        return c == null ? "No course found for code: " + code : c.toString();
    }

    public MyArray<Course> searchCourseByName(String fragment) {
        return courseManager.searchByNameContains(fragment);
    }

    public String viewStudentSchedule(String studentId) {
        if (studentId == null) return "Invalid input.";
        Student stu = students.get(normalize(studentId));
        if (stu == null) return "Student not found.";
        MyArray<String> codes = stu.getEnrolledCourseCodes();
        if (codes.size() == 0) return "No courses enrolled.";
        StringBuilder sb = new StringBuilder();
        sb.append("Schedule for ").append(stu.getId()).append(" - ").append(stu.getName()).append(":\n");
        for (int i = 0; i < codes.size(); i++) {
            String code = codes.get(i);
            Course c = courseManager.getCourse(code);
            sb.append(" - ").append(c != null ? c.toString() : code + " (missing)").append("\n");
        }
        return sb.toString();
    }

    public void displayAllStudents() {
        for (int i = 0; i < studentIds.size(); i++) {
            String id = studentIds.get(i);
            Student s = students.get(id);
            if (s != null) System.out.println(s);
        }
    }

    private String normalize(String s) { return s == null ? null : s.trim().toUpperCase(); }
}
