package task;

/**
 * Represents a task in the chatbot system. A Task object minimally
 * contains a description and done status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    /**
     * Sets the task's done status to be true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the task's done status to be false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task's done status.
     *
     * @return done status
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns a string representation of the task to be used when saving locally.
     *
     * @return string representation of the task
     */
    public abstract String toDataString();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
