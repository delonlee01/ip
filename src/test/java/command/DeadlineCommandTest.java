package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import task.TaskList;
import woody.Ui;

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
            COMMAND.execute(new TaskList(), new Ui());
            String expected = "Got it. I've added this task:" + System.lineSeparator()
                    + "[D][ ] test task 1 (by: 01 Jan 2025)" + System.lineSeparator()
                    + "Now you have 1 tasks in the list." + System.lineSeparator();
            assertEquals(expected, OUTPUT.toString());
        }
    }

    @Test
    public void isReadOnly_success() {
        assertFalse(COMMAND.isReadOnly());
    }

    @Test
    public void isExit_success() {
        assertFalse(COMMAND.isExit());
    }
}
