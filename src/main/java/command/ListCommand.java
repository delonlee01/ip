package command;

import java.util.ArrayList;

import task.Task;
import task.TaskList;

import woody.Ui;

/**
 * Represents a list command in the chatbot system.
 */
public class ListCommand extends Command {
    protected static String REGEX_PATTERN = "^list$";

    /**
     * Returns a ListCommand if the specified input matches the usage
     * format.
     *
     * @param String input
     * @return ListCommand
     */
    public static ListCommand createCommandIfValid(String input) {
        if (input.matches(REGEX_PATTERN)) {
            return new ListCommand();
        }
        return null;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ArrayList<Task> tasks = taskList.getTasks();
        ui.writeOutput("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            ui.writeOutput(String.format("%d.%s", i + 1, task));
        }
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }
}
