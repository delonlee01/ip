package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the chatbot system. A
 * Deadline object contains a description, done status, and 'by'
 * date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and 'by' date.
     *
     * @param description description of task
     * @param by          due date;
     */
    public Deadline(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Returns the 'by' date of the Event task.
     *
     * @return LocalDate 'by' date
     */
    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toDataString() {
        int status = this.isDone ? 1 : 0;
        return String.format("%d|deadline %s /by %s", status, this.description,
                this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
}
