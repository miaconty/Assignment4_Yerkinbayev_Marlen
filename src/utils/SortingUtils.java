package utils;

import model.Course;
import java.util.List;

public class SortingUtils {
    public static void sortCoursesByName(List<Course> courses) {
        courses.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    }
}
