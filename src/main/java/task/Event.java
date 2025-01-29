package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    @Override
    public String toDataString() {
        int status = this.isDone ? 1 : 0;
        return String.format("E|%d|%s /from %s /to %s", status, this.description,
                this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
}
