package task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the chatbot system.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty <code>TaskList</code>.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new <code>TaskList</code> with the elements of the specified
     * <code>ArrayList</code>.
     * 
     * @param ArrayList<Task> tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns an <code>ArrayList</code> which contains the tasks in the
     * <code>TaskList</code>.
     * 
     * @return ArrayList<Task> tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a <code>Task</code> from the <code>TaskList</code> at the specified
     * index.
     * 
     * @param int index
     * @return Task task
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the <code>TaskList</code>.
     * 
     * @return int size
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Adds a new task to the <code>TaskList</code>.
     * 
     * @param Task task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the <code>TaskList</code> at the specified index.
     * 
     * @param int index
     * @return Task task removed
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }
}
