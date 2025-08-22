package src.model;

public class Registration {
    private final Student student;
    private final Course course;

    public Registration(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }

    @Override
    public String toString() {
        return "Registration: " + (student == null ? "null" : student.getId())
                + " -> " + (course == null ? "null" : course.getCode());
    }
}
