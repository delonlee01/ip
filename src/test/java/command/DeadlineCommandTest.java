package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import task.TaskList;

public class DeadlineCommandTest {
    private static final DeadlineCommand COMMAND = new DeadlineCommand("test task 1",
            LocalDate.parse("01/01/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    @Test
    public void createCommandIfValid_invalid_success() {
        assertNull(DeadlineCommand.createCommandIfValid("deadlinee"));
    }

    @Test
    public void createCommandIfValid_valid_success() {
        assertNotNull(DeadlineCommand.createCommandIfValid("deadline test task 1 /by 01/01/2025"));
    }

    @Test
    public void execute_success() {
        String expected = """
                Got it. I've added this task:
                [D][ ] test task 1 (by: 01 Jan 2025)
                Now you have 1 tasks in the list.
                """;
        assertEquals(expected, COMMAND.execute(new TaskList()));
    }

    @Test
    public void isReadOnly_success() {
        assertFalse(COMMAND.isReadOnly());
    }
}
