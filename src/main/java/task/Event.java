package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task in the chatbot system. An Event object contains a
 * description, done status, 'from' date, and 'to' date.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs a Deadline task with the specified description, 'from' date, and 'to' date.
     *
     * @param description description of task
     * @param from        start date;
     * @param to          end date;
     */
    public Event(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the 'from' date of the Event task.
     *
     * @return LocalDate 'from' date
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the 'to' date of the Event task.
     *
     * @return LocalDate 'to' date
     */
    public LocalDate getTo() {
        return this.to;
    }

    @Override
    public String toDataString() {
        int status = this.isDone ? 1 : 0;
        String tags = this.getTags().replace("#", "");
        return String.format("%d|event %s /from %s /to %s|%s", status, this.description,
                this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                tags.isEmpty() ? " " : tags);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
}
