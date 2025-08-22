package src.model;

import src.datastructures.MyArray;

public class Student {
    private final String id;
    private final String name;
    private final MyArray<String> enrolledCourseCodes;

    public Student(String id, String name) {
        this.id = normalize(id);
        this.name = name;
        this.enrolledCourseCodes = new MyArray<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public MyArray<String> getEnrolledCourseCodes() { return enrolledCourseCodes; }

    public boolean isEnrolledIn(String courseCode) {
        if (courseCode == null) return false;
        String code = normalize(courseCode);
        for (int i = 0; i < enrolledCourseCodes.size(); i++) {
            String c = enrolledCourseCodes.get(i);
            if (c != null && c.equalsIgnoreCase(code)) return true;
        }
        return false;
    }

    public void addCourse(String courseCode) {
        if (courseCode == null) return;
        String code = normalize(courseCode);
        if (!isEnrolledIn(code)) enrolledCourseCodes.add(code);
    }

    public void dropCourse(String courseCode) {
        if (courseCode == null) return;
        String code = normalize(courseCode);
        for (int i = 0; i < enrolledCourseCodes.size(); i++) {
            String c = enrolledCourseCodes.get(i);
            if (c != null && c.equalsIgnoreCase(code)) {
                enrolledCourseCodes.removeAt(i);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return id + " - " + name + " (Courses: " + enrolledCourseCodes.size() + ")";
    }

    private String normalize(String s) { return s == null ? null : s.trim().toUpperCase(); }
}
