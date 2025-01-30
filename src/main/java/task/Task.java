package task;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public abstract String toDataString();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
