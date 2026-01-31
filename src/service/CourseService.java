package service;

import repository.CourseRepository;
import repository.interfaces.ICrudRepository;
import model.Course;
import exception.*;
import utils.SortingUtils;
import java.util.List;

public class CourseService {
    private final ICrudRepository<Course> repository;

    public CourseService() {
        this.repository = new CourseRepository();
    }

    public void registerCourse(Course course) throws CourseAppException {
        validate(course);
        repository.create(course);
    }

    public List<Course> getAllCourses() throws CourseAppException {
        List<Course> courses = repository.getAll();
        SortingUtils.sortCoursesByName(courses);
        return courses;
    }

    public void updateCourse(int id, Course updatedCourse) throws CourseAppException {
        if (repository.getById(id) == null) {
            throw new ResourceNotFoundException("Course with ID " + id + " not found.");
        }
        validate(updatedCourse);
        repository.update(id, updatedCourse);
    }

    public void deleteCourse(int id) throws CourseAppException {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            throw new ResourceNotFoundException("Course with ID " + id + " not found.");
        }
    }

    private void validate(Course course) throws InvalidInputException {
        if (course.getName() == null || course.getName().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty.");
        }
        if (course.getCreditValue() <= 0) {
            throw new InvalidInputException("Credits must be positive.");
        }
    }
}
