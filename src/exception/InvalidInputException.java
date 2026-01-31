package exception;
public class InvalidInputException extends CourseAppException {
    public InvalidInputException(String message) { super("Invalid Input: " + message); }
}