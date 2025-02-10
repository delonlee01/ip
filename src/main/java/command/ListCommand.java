package command;

import java.util.ArrayList;

import task.Task;
import task.TaskList;

/**
 * Represents a list command in the chatbot system.
 */
public class ListCommand extends Command {
    private static final String REGEX_PATTERN = "^list$";

    /**
     * Returns a ListCommand if the specified input matches the usage format.
     *
     * @param input string representation of command
     * @return ListCommand
     */
    public static ListCommand createCommandIfValid(String input) {
        if (!input.matches(REGEX_PATTERN)) {
            return null;
        }
        return new ListCommand();
    }

    @Override
    public String execute(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            output.append(String.format("%d.%s\n", i + 1, task));
        }
        return output.toString();
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }
}
