package task;

/**
 * Represents a Todo task in the chatbot system. A
 * Todo object contains a description and done status.
 */
public class Todo extends Task {
    public Todo(String description) {
        this.description = description;
    }

    @Override
    public String toDataString() {
        int status = this.isDone ? 1 : 0;
        return String.format("%d|todo %s", status, this.description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
