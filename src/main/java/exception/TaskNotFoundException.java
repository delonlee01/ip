package exception;

/**
 * Represents an exception that is thrown when a task is not found.
 */
public class TaskNotFoundException extends WoodyException {
    public TaskNotFoundException() {
        super("The specified task does not exist.");
    }
}
