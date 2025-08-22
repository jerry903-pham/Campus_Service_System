package src.model;

import src.datastructures.MyArray;
import src.datastructures.MyQueue;

public class Course {
    private final String code;
    private final String name;
    private final int capacity;
    private int enrolledCount;

    private final MyArray<String> enrolledStudentIds;
    private final MyQueue<String> waitlist;

    public Course(String code, String name, int capacity) {
        this.code = normalize(code);
        this.name = name;
        this.capacity = capacity > 0 ? capacity : 30;
        this.enrolledCount = 0;
        this.enrolledStudentIds = new MyArray<>();
        this.waitlist = new MyQueue<>();
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public int getEnrolledCount() { return enrolledCount; }
    public int getWaitlistSize() { return waitlist.size(); }

    public boolean isFull() { return enrolledCount >= capacity; }

    public boolean isEnrolled(String studentId) {
        if (studentId == null) return false;
        String id = normalize(studentId);
        for (int i = 0; i < enrolledStudentIds.size(); i++) {
            String s = enrolledStudentIds.get(i);
            if (s != null && s.equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    public boolean enrollNow(String studentId) {
        if (studentId == null) return false;
        if (isFull() || isEnrolled(studentId)) return false;
        enrolledStudentIds.add(normalize(studentId));
        enrolledCount++;
        return true;
    }

    public String dropAndMaybeFillFromWaitlist(String studentId) {
        if (studentId != null) {
            String id = normalize(studentId);
            for (int i = 0; i < enrolledStudentIds.size(); i++) {
                String s = enrolledStudentIds.get(i);
                if (s != null && s.equalsIgnoreCase(id)) {
                    enrolledStudentIds.removeAt(i);
                    enrolledCount--;
                    break;
                }
            }
        }
        String next = waitlist.dequeue();
        if (next != null) {
            enrolledStudentIds.add(next);
            enrolledCount++;
        }
        return next;
    }

    public void enqueueWaitlist(String studentId) {
        if (studentId == null) return;
        String id = normalize(studentId);
        int n = waitlist.size();
        boolean exists = false;
        for (int i = 0; i < n; i++) {
            String x = waitlist.dequeue();
            if (x != null && x.equalsIgnoreCase(id)) exists = true;
            waitlist.enqueue(x);
        }
        if (!exists) waitlist.enqueue(id);
    }

    @Override
    public String toString() {
        return code + " - " + name +
                " (Capacity: " + capacity +
                ", Enrolled: " + enrolledCount +
                ", Waitlist: " + waitlist.size() + ")";
    }

    private String normalize(String s) { return s == null ? null : s.trim().toUpperCase(); }
}
