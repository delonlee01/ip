package task;

public class Todo extends Task {
    public Todo(String args) {
        this.description = args.strip();
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
