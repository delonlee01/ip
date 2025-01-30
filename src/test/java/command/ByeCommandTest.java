package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import task.TaskList;
import woody.Ui;

public class ByeCommandTest {
    private static final ByeCommand COMMAND = new ByeCommand();

    @Test
    public void createCommandIfValid_invalid_success() {
        assertNull(ByeCommand.createCommandIfValid("byee"));
    }

    @Test
    public void createCommandIfValid_valid_success() {
        assertNotNull(ByeCommand.createCommandIfValid("bye"));
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
            String expected = "Bye! See you soon partner!" + System.lineSeparator();
            assertEquals(expected, OUTPUT.toString());
        }
    }

    @Test
    public void isReadOnly_success() {
        assertFalse(COMMAND.isReadOnly());
    }

    @Test
    public void isExit_success() {
        assertTrue(COMMAND.isExit());
    }
}
