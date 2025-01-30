package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import task.Deadline;
import task.Event;
import task.TaskList;
import woody.Ui;

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

    @Nested
    public class PrintTest {
        private static final ByteArrayOutputStream OUTPUT = new ByteArrayOutputStream();
        private static final PrintStream ORIGINAL_OUTPUT = System.out;

        @BeforeAll
        public static void setUp() {
            System.setOut(new PrintStream(OUTPUT));
        }

        @AfterAll
        public static void teardown() {
            System.setOut(ORIGINAL_OUTPUT);
        }

        @Test
        public void execute_success() {
            TaskList taskList = new TaskList();
            taskList.addTask(new Deadline("test task 1", DATE_1));
            taskList.addTask(new Deadline("test task 2", DATE_2));
            taskList.addTask(new Event("test task 3", DATE_1, DATE_2));
            taskList.addTask(new Event("test task 4", DATE_2, DATE_3));
            try {
                COMMAND.execute(taskList, new Ui());
                String expected = "Here are the tasks for 02 Jan 2025:" + System.lineSeparator()
                        + "2.[D][ ] test task 2 (by: 02 Jan 2025)" + System.lineSeparator()
                        + "3.[E][ ] test task 3 (from: 01 Jan 2025 to: 02 Jan 2025)" + System.lineSeparator()
                        + "4.[E][ ] test task 4 (from: 02 Jan 2025 to: 03 Jan 2025)" + System.lineSeparator();
                assertEquals(expected, OUTPUT.toString());
            } catch (Exception e) {
                fail();
            }
        }
    }

    @Test
    public void isReadOnly_success() {
        assertTrue(COMMAND.isReadOnly());
    }

    @Test
    public void isExit_success() {
        assertFalse(COMMAND.isExit());
    }
}
