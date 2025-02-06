package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

public class FindCommandTest {
    private static final LocalDate DATE_1 = LocalDate.parse("01/01/2025",
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private static final LocalDate DATE_2 = LocalDate.parse("02/01/2025",
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private static final FindCommand COMMAND = new FindCommand("match");

    @Test
    public void createCommandIfValid_invalid_success() {
        assertNull(FindCommand.createCommandIfValid("findd"));
    }

    @Test
    public void createCommandIfValid_valid_success() {
        assertNotNull(FindCommand.createCommandIfValid("find test"));
    }

    @Test
    public void execute_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("test task 1 match"));
        taskList.addTask(new Deadline("test match task 2", DATE_1));
        taskList.addTask(new Event("test task 3", DATE_1, DATE_2));
        String expected = """
                Here are the matching tasks in your list:
                1.[T][ ] test task 1 match
                2.[D][ ] test match task 2 (by: 01 Jan 2025)
                """;
        assertEquals(expected, COMMAND.execute(taskList));
    }

    @Test
    public void isReadOnly_success() {
        assertTrue(COMMAND.isReadOnly());
    }
}
