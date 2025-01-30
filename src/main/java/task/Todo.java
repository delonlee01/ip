package task;

/**
 * Represents a <code>Todo</code> task in the chatbot system. A
 * <code>Todo</code> object contains a description and done status.
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
