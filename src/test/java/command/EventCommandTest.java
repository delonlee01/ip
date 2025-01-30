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
                    + "[E][ ] test task 1 (from: 01 Jan 2025 to: 02 Jan 2025)" + System.lineSeparator()
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
