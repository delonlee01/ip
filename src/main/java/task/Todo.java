package task;

/**
 * Represents a Todo task in the chatbot system. A Todo object contains
 * a description and done status.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description description of task
     */
    public Todo(String description) {
        this.description = description;
    }

    @Override
    public String toDataString() {
        int status = this.isDone ? 1 : 0;
        String tags = this.getTags().replace("#", "");
        return String.format("%d|todo %s|%s", status,
                this.description, tags.isEmpty() ? " " : tags);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
