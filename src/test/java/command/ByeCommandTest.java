package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import task.TaskList;

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

    @Test
    public void execute_success() {
        assertEquals("Bye! See you soon partner!", COMMAND.execute(new TaskList()));
    }

    @Test
    public void isReadOnly_success() {
        assertFalse(COMMAND.isReadOnly());
    }
}
