package task;

import exception.InvalidArgumentsException;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String args) throws InvalidArgumentsException {
        String[] tokens = args.split("(/from|/to)");
        if (tokens.length != 3) {
            throw new InvalidArgumentsException("\"event\" requires a description, from, and to.");
        }
        this.description = tokens[0].strip();
        this.from = tokens[1].strip();
        this.to = tokens[2].strip();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
