package exception;
public class DatabaseOperationException extends CourseAppException {
    public DatabaseOperationException(String message) { super("Database Error: " + message); }
}