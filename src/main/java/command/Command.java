package command;

import exception.WoodyException;

import task.TaskList;

import woody.Ui;

/**
 * Represents a command in the chatbot system.
 */
public abstract class Command {
    /**
     * Executes the logic of the command.
     * 
     * @param TaskList tasks
     * @param Ui       ui
     * @throws WoodyException
     */
    public abstract void execute(TaskList tasks, Ui ui) throws WoodyException;

    /**
     * Returns if the commmand is read-only, or read-write.
     * 
     * @return boolean read-only status
     */
    public boolean isReadOnly() {
        return false;
    }

    /**
     * Returns if application should exit after the commmand is executed.
     * 
     * @return boolean exit status
     */
    public boolean isExit() {
        return false;
    }
}
