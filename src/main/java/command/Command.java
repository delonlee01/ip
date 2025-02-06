package command;

import exception.WoodyException;
import task.TaskList;

/**
 * Represents a command in the chatbot system.
 */
public abstract class Command {
    /**
     * Executes the logic of the command.
     *
     * @param tasks TaskList
     * @return Woody's response
     * @throws WoodyException
     */
    public abstract String execute(TaskList tasks) throws WoodyException;

    /**
     * Returns if the command is read-only, or read-write.
     *
     * @return read-only status
     */
    public boolean isReadOnly() {
        return false;
    }
}
