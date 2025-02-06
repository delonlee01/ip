package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import task.TaskList;

public class TodoCommandTest {
    private static final TodoCommand COMMAND = new TodoCommand("test task 1");

    @Test
    public void createCommandIfValid_invalid_success() {
        assertNull(TodoCommand.createCommandIfValid("todoo"));
    }

    @Test
    public void createCommandIfValid_valid_success() {
        assertNotNull(TodoCommand.createCommandIfValid("todo test task 1"));
    }

    @Test
    public void execute_success() {
        String expected = """
                Got it. I've added this task:
                [T][ ] test task 1
                Now you have 1 tasks in the list.
                """;
        assertEquals(expected, COMMAND.execute(new TaskList()));
    }

    @Test
    public void isReadOnly_success() {
        assertFalse(COMMAND.isReadOnly());
    }
}
