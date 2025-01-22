public class TaskNotFoundException extends WoodyException {
    public TaskNotFoundException() {
        super("The specified task does not exist.");
    }
}
