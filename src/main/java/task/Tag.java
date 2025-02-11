package task;

/**
 * Represents a tag for a task in the chatbot system.
 */
public class Tag {
    private final String label;

    public Tag(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return String.format("#%s", this.label);
    }
}
