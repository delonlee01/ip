package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidArgumentsException;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String args) throws InvalidArgumentsException {
        String[] tokens = args.split("(/from|/to)");
        if (tokens.length != 3) {
            throw new InvalidArgumentsException("\"event\" requires a description, from, and to.");
        }
        this.description = tokens[0].strip();
        try {
            this.from = LocalDate.parse(tokens[1].strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.to = LocalDate.parse(tokens[2].strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentsException("\"event\" requires from and to in the format: dd/MM/yyyy.");
        }
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
