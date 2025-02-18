package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exception.TaskNotFoundException;
import task.Task;
import task.TaskList;
import task.Todo;

public class UnmarkCommandTest {
    private static final UnmarkCommand COMMAND = new UnmarkCommand(0);

    @Test
    public void createCommandIfValid_invalid_success() {
        assertNull(UnmarkCommand.createCommandIfValid("unmarkk"));
    }

    @Test
    public void createCommandIfValid_valid_success() {
        assertNotNull(UnmarkCommand.createCommandIfValid("unmark 0"));
    }

    @Test
    public void execute_exception() {
        assertThrows(TaskNotFoundException.class, () -> COMMAND.execute(new TaskList()));
    }

    @Test
    public void execute_isDone_success() {
        TaskList taskList = new TaskList();
        Task task = new Todo("test task 1");
        task.markAsDone();
        taskList.addTask(task);
        try {
            String expected = """
                    Alright! I've marked this task as not done yet:
                    [T][ ] test task 1
                    """;
            assertEquals(expected, COMMAND.execute(taskList));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void execute_isNotDone_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("test task 1"));
        try {
            String expected = """
                    Shoot! This task is already marked as not done yet:
                    [T][ ] test task 1
                    """;
            assertEquals(expected, COMMAND.execute(taskList));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void isReadOnly_success() {
        assertFalse(COMMAND.isReadOnly());
    }
}
