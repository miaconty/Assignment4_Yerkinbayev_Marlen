package exception;
public class ResourceNotFoundException extends CourseAppException {
    public ResourceNotFoundException(String message) { super("Not Found: " + message); }
}