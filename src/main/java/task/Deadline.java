package task;

import exception.InvalidArgumentsException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String args) throws InvalidArgumentsException {
        String[] tokens = args.split(" /by ");
        if (tokens.length != 2) {
            throw new InvalidArgumentsException("\"deadline\" requires a description, and by.");
        }
        this.description = tokens[0].strip();
        this.by = tokens[1].strip();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
