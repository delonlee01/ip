package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import task.Task;
import task.TaskList;
import task.Todo;
import woody.Ui;

public class ListCommandTest {
    private final ListCommand COMMAND = new ListCommand();

    @Test
    public void createCommandIfValid_invalid_success() {
        assertNull(ListCommand.createCommandIfValid("lists"));
    }

    @Test
    public void createCommandIfValid_valid_success() {
        assertNotNull(ListCommand.createCommandIfValid("list"));
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
            ArrayList<Task> tasks = new ArrayList<>();
            tasks.add(new Todo("test task 1"));
            tasks.add(new Todo("test task 2"));
            COMMAND.execute(new TaskList(tasks), new Ui());
            String expected = "Here are the tasks in your list:" + System.lineSeparator()
                    + "1.[T][ ] test task 1" + System.lineSeparator()
                    + "2.[T][ ] test task 2" + System.lineSeparator();
            assertEquals(expected, OUTPUT.toString());
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
