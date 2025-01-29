package command;

import exception.WoodyException;

import task.TaskList;

import woody.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws WoodyException;

    public boolean isReadOnly() {
        return false;
    }

    public boolean isExit() {
        return false;
    }
}
