package task;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    private final TaskList EMPTY_TASK_LIST = new TaskList();
    private final Todo TASK_1 = new Todo("test task 1");
    private final Todo TASK_2 = new Todo("test task 2");
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(TASK_1);
        tasks.add(TASK_2);
        taskList = new TaskList(tasks);
    }

    @Test
    public void getTasks_empty_success() {
        assertEquals(new ArrayList<Task>(), EMPTY_TASK_LIST.getTasks());
    }

    @Test
    public void getTasks_populated_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(TASK_1);
        tasks.add(TASK_2);
        assertEquals(tasks, taskList.getTasks());
    }

    @Test
    public void getTask_empty_exception() {
        assertThrows(IndexOutOfBoundsException.class, () -> EMPTY_TASK_LIST.getTask(0));
    }

    @Test
    public void getTask_populated_success() {
        assertEquals(TASK_1, taskList.getTask(0));
    }

    @Test
    public void getTask_populated_exception() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(3));
    }

    @Test
    public void getTaskCount_empty_success() {
        assertEquals(0, EMPTY_TASK_LIST.getTaskCount());
    }

    @Test
    public void getTaskCount_populated_success() {
        assertEquals(2, taskList.getTaskCount());
    }

    @Test
    public void addTask_success() {
        Todo task = new Todo("test task 3");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(2));
    }

    @Test
    public void deleteTask_empty_exception() {
        assertThrows(IndexOutOfBoundsException.class, () -> EMPTY_TASK_LIST.deleteTask(0));
    }

    @Test
    public void deleteTask_populated_success() {
        Task task = taskList.deleteTask(0);
        assertEquals(task, TASK_1);
        assertEquals(1, taskList.getTaskCount());
    }
}
