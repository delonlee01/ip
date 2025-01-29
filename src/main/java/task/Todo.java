package task;

public class Todo extends Task {
    public Todo(String description) {
        this.description = description;
    }

    @Override
    public String toDataString() {
        int status = this.isDone ? 1 : 0;
        return String.format("T|%d|%s", status, this.description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
