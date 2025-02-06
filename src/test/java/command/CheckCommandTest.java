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

public class CheckCommandTest {
    private static final LocalDate DATE_1 = LocalDate.parse("01/01/2025",
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private static final LocalDate DATE_2 = LocalDate.parse("02/01/2025",
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private static final LocalDate DATE_3 = LocalDate.parse("03/01/2025",
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private static final CheckCommand COMMAND = new CheckCommand(DATE_2);

    @Test
    public void createCommandIfValid_invalid_success() {
        assertNull(CheckCommand.createCommandIfValid("checkk"));
    }

    @Test
    public void createCommandIfValid_valid_success() {
        assertNotNull(CheckCommand.createCommandIfValid("check 01/01/2025"));
    }

    @Test
    public void execute_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Deadline("test task 1", DATE_1));
        taskList.addTask(new Deadline("test task 2", DATE_2));
        taskList.addTask(new Event("test task 3", DATE_1, DATE_2));
        taskList.addTask(new Event("test task 4", DATE_2, DATE_3));
        String expected = """
                Here are the tasks for 02 Jan 2025:
                2.[D][ ] test task 2 (by: 02 Jan 2025)
                3.[E][ ] test task 3 (from: 01 Jan 2025 to: 02 Jan 2025)
                4.[E][ ] test task 4 (from: 02 Jan 2025 to: 03 Jan 2025)
                """;
        assertEquals(expected, COMMAND.execute(taskList));
    }

    @Test
    public void isReadOnly_success() {
        assertTrue(COMMAND.isReadOnly());
    }
}
