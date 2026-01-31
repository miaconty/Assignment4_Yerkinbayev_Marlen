package controller;

import model.*;
import service.CourseService;
import exception.CourseAppException;
import utils.ReflectionUtils;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("ASSIGNMENT 4");


        ReflectionUtils.inspectClass(OnlineCourse.class);

        CourseService service = new CourseService();
        Instructor i1 = new Instructor(1, "Dr. Smith", "smith@uni.edu");

        System.out.println("\n--- 1. Registering Courses ---");
        Course c1 = new OnlineCourse(0, "Java Advanced", 5, i1, "udemy.com");
        Course c2 = new OnCampusCourse(0, "Data Structures", 4, i1, "Room 304");
        Course c3 = new OnlineCourse(0, "Algorithms", 6, i1, "coursera.org");

        try {
            service.registerCourse(c1);
            service.registerCourse(c2);
            service.registerCourse(c3);
            System.out.println("Courses registered.");
        } catch (CourseAppException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\n--- 2. Read All (Sorted by Name via Lambda) ---");
        try {
            List<Course> list = service.getAllCourses();
            for (Course c : list) {
                c.displayInfo();
                if (c.isHighCreditCourse()) {
                    System.out.println("   >>> High Credit Course! (>3)");
                }
            }
        } catch (CourseAppException e) {
            e.printStackTrace();
        }
    }
}