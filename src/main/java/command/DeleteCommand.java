package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.TaskNotFoundException;
import task.Task;
import task.TaskList;

/**
 * Represents a delete command in the chatbot system.
 */
public class DeleteCommand extends Command {
    private static final Pattern REGEX_PATTERN = Pattern.compile("^delete (?<index>\\d+)$");
    private final int index;

    /**
     * Constructs a command to delete the task at the specified index.
     *
     * @param index index of task
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns a DeleteCommand if the specified input matches the
     * usage format.
     *
     * @param input string representation of command
     * @return DeleteCommand
     */
    public static DeleteCommand createCommandIfValid(String input) {
        Matcher matcher = REGEX_PATTERN.matcher(input);
        if (!matcher.matches()) {
            return null;
        }
        int index = Integer.parseInt(matcher.group("index")) - 1;
        return new DeleteCommand(index);
    }

    @Override
    public String execute(TaskList taskList) throws TaskNotFoundException {
        try {
            Task task = taskList.deleteTask(this.index);
            String output = "Got it. I've removed this task:\n";
            output += task.toString() + "\n";
            output += String.format("Now you have %d tasks in the list.\n", taskList.getTaskCount());
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }
}
