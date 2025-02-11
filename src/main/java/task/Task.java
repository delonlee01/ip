package task;

import java.util.ArrayList;

/**
 * Represents a task in the chatbot system. A Task object minimally
 * contains a description and done status.
 */
public abstract class Task {
    protected String description;
    protected ArrayList<Tag> tags = new ArrayList<>();
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
     * Returns the tags of the task as a string.
     *
     * @return tags
     */
    public String getTags() {
        return this.tags.stream()
                .map(Tag::toString)
                .reduce("", (result, current) -> result + current + " ")
                .trim();
    }

    /**
     * Adds the given tag to the task.
     */
    public void addTag(Tag tag) {
        this.tags.add(tag);
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
        return String.format("[%s] %s %s", this.getStatusIcon(), this.description, this.getTags()).trim();
    }
}
