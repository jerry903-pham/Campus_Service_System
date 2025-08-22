package src.service;

import src.datastructures.MyArray;
import src.datastructures.MyHashTable;
import model.Course;

public class CourseManager {
    private final MyHashTable<String, Course> courses;
    private final MyArray<String> courseCodes;

    public CourseManager() {
        this.courses = new MyHashTable<>();
        this.courseCodes = new MyArray<>();
    }

    public boolean addCourse(Course course) {
        if (course == null || course.getCode() == null) return false;
        String code = normalize(course.getCode());
        if (courses.get(code) != null) return false;
        courses.put(code, course);
        courseCodes.add(code);
        return true;
    }

    public Course getCourse(String code) {
        return code == null ? null : courses.get(normalize(code));
    }

    public boolean removeCourse(String code) {
        if (code == null) return false;
        String k = normalize(code);
        if (courses.remove(k) == null) return false; // assumes remove returns V or null
        for (int i = 0; i < courseCodes.size(); i++) {
            String c = courseCodes.get(i);
            if (c != null && c.equalsIgnoreCase(k)) {
                courseCodes.removeAt(i);
                break;
            }
        }
        return true;
    }

    public MyArray<Course> getAllCoursesSnapshot() {
        MyArray<Course> out = new MyArray<>(courseCodes.size());
        for (int i = 0; i < courseCodes.size(); i++) {
            String code = courseCodes.get(i);
            Course c = courses.get(code);
            if (c != null) out.add(c);
        }
        return out;
    }

    public MyArray<Course> searchByNameContains(String fragment) {
        MyArray<Course> out = new MyArray<>();
        if (fragment == null || fragment.trim().isEmpty()) return out;
        String q = fragment.trim().toLowerCase();
        for (int i = 0; i < courseCodes.size(); i++) {
            String code = courseCodes.get(i);
            Course c = courses.get(code);
            if (c != null && c.getName() != null && c.getName().toLowerCase().contains(q)) out.add(c);
        }
        return out;
    }

    public void displayAllCourses() {
        for (int i = 0; i < courseCodes.size(); i++) {
            String code = courseCodes.get(i);
            Course c = courses.get(code);
            if (c != null) System.out.println(c);
        }
    }

    private String normalize(String s) { return s == null ? null : s.trim().toUpperCase(); }
}
