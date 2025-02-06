package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.TaskNotFoundException;
import task.Task;
import task.TaskList;

/**
 * Represents an unmark command in the chatbot system.
 */
public class UnmarkCommand extends Command {
    private static final Pattern REGEX_PATTERN = Pattern.compile("^unmark (?<index>\\d+)$");
    private final int index;

    /**
     * Constructs a command to mark the task at the specified index as not done.
     *
     * @param index index of task
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Returns an UnmarkCommand if the specified input matches the usage format.
     *
     * @param input string representation of command
     * @return UnmarkCommand
     */
    public static UnmarkCommand createCommandIfValid(String input) {
        Matcher matcher = REGEX_PATTERN.matcher(input);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group("index")) - 1;
            return new UnmarkCommand(index);
        }
        return null;
    }

    @Override
    public String execute(TaskList taskList) throws TaskNotFoundException {
        try {
            Task task = taskList.getTask(this.index);
            task.markAsNotDone();
            return "Alright! I've marked this task as not done yet:\n" + task + "\n";
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }
}
