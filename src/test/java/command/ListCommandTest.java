package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import task.Task;
import task.TaskList;
import task.Todo;

public class ListCommandTest {
    private static final ListCommand COMMAND = new ListCommand();

    @Test
    public void createCommandIfValid_invalid_success() {
        assertNull(ListCommand.createCommandIfValid("lists"));
    }

    @Test
    public void createCommandIfValid_valid_success() {
        assertNotNull(ListCommand.createCommandIfValid("list"));
    }

    @Test
    public void execute_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test task 1"));
        tasks.add(new Todo("test task 2"));
        String expected = """
                Here are the tasks in your list:
                1.[T][ ] test task 1
                2.[T][ ] test task 2
                """;
        assertEquals(expected, COMMAND.execute(new TaskList(tasks)));
    }

    @Test
    public void isReadOnly_success() {
        assertTrue(COMMAND.isReadOnly());
    }
}
