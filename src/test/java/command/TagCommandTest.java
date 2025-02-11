package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exception.TaskNotFoundException;
import task.TaskList;
import task.Todo;

public class TagCommandTest {
    private static final TagCommand COMMAND = new TagCommand(0, "testing");

    @Test
    public void createCommandIfValid_invalid_success() {
        assertNull(TagCommand.createCommandIfValid("tagg"));
    }

    @Test
    public void createCommandIfValid_valid_success() {
        assertNotNull(TagCommand.createCommandIfValid("tag 0 testing"));
    }

    @Test
    public void execute_exception() {
        assertThrows(TaskNotFoundException.class, () -> COMMAND.execute(new TaskList()));
    }

    @Test
    public void execute_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("test task 1"));
        try {
            String expected = """
                    Alright! I've tagged this task:
                    [T][ ] test task 1 #testing
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
