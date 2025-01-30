package task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the chatbot system.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with the elements of the specified
     * ArrayList.
     *
     * @param ArrayList<Task> tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns an ArrayList which contains the tasks in the
     * TaskList.
     *
     * @return ArrayList<Task> tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a Task from the TaskList at the specified
     * index.
     *
     * @param int index
     * @return Task task
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return int size
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param Task task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the TaskList at the specified index.
     *
     * @param int index
     * @return Task task removed
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }
}
