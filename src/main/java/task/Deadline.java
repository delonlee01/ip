package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidArgumentsException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String args) throws InvalidArgumentsException {
        String[] tokens = args.split(" /by ");
        if (tokens.length != 2) {
            throw new InvalidArgumentsException("\"deadline\" requires a description, and by.");
        }
        this.description = tokens[0].strip();
        try {
            this.by = LocalDate.parse(tokens[1].strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentsException("\"deadline\" requires by in the format: dd/MM/yyyy.");
        }
    }

    @Override
    public String toDataString() {
        int status = this.isDone ? 1 : 0;
        return String.format("D|%d|%s /by %s", status, this.description,
                this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
}
