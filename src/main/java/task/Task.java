package task;

/**
 * Represents a task in the chatbot system. A <code>Task</code> object minimally
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
     * Returns a string representation of the task's done status.
     * 
     * @return String done status
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns a string representation of the task to be used when saving locally.
     * 
     * @return String task
     */
    public abstract String toDataString();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
