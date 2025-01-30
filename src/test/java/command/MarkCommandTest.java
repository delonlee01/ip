package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import exception.TaskNotFoundException;
import task.TaskList;
import task.Todo;
import woody.Ui;

public class MarkCommandTest {
    private static final MarkCommand COMMAND = new MarkCommand(0);

    @Test
    public void createCommandIfValid_invalid_success() {
        assertNull(MarkCommand.createCommandIfValid("markk"));
    }

    @Test
    public void createCommandIfValid_valid_success() {
        assertNotNull(MarkCommand.createCommandIfValid("mark 0"));
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
        public void execute_exception() {
            assertThrows(TaskNotFoundException.class, () -> COMMAND.execute(new TaskList(), new Ui()));
        }

        @Test
        public void execute_success() {
            TaskList taskList = new TaskList();
            taskList.addTask(new Todo("test task 1"));
            try {
                COMMAND.execute(taskList, new Ui());
                String expected = "Yee-haw! I've marked this task as done:" + System.lineSeparator()
                        + "[T][X] test task 1" + System.lineSeparator();
                assertEquals(expected, OUTPUT.toString());
            } catch (Exception e) {
                fail();
            }
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
