package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import task.TaskList;

public class EventCommandTest {
    private static final EventCommand COMMAND = new EventCommand("test task 1",
            LocalDate.parse("01/01/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            LocalDate.parse("02/01/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    @Test
    public void createCommandIfValid_invalid_success() {
        assertNull(EventCommand.createCommandIfValid("eventt"));
    }

    @Test
    public void createCommandIfValid_valid_success() {
        assertNotNull(EventCommand.createCommandIfValid("event test task 1 /from 01/01/2025 /to 02/01/2025"));
    }

    @Test
    public void execute_success() {
        String expected = """
                Got it. I've added this task:
                [E][ ] test task 1 (from: 01 Jan 2025 to: 02 Jan 2025)
                Now you have 1 tasks in the list.
                """;
        assertEquals(expected, COMMAND.execute(new TaskList()));
    }

    @Test
    public void isReadOnly_success() {
        assertFalse(COMMAND.isReadOnly());
    }
}
