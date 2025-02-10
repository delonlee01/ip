package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.TaskNotFoundException;
import task.Task;
import task.TaskList;

/**
 * Represents a mark command in the chatbot system.
 */
public class MarkCommand extends Command {
    private static final Pattern REGEX_PATTERN = Pattern.compile("^mark (?<index>\\d+)$");
    private final int index;

    /**
     * Constructs a command to mark the task at the specified index as done.
     *
     * @param index index of task
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Returns a MarkCommand if the specified input matches the usage format.
     *
     * @param input string representation of command
     * @return MarkCommand
     */
    public static MarkCommand createCommandIfValid(String input) {
        Matcher matcher = REGEX_PATTERN.matcher(input);
        if (!matcher.matches()) {
            return null;
        }
        int index = Integer.parseInt(matcher.group("index")) - 1;
        return new MarkCommand(index);
    }

    @Override
    public String execute(TaskList taskList) throws TaskNotFoundException {
        try {
            Task task = taskList.getTask(this.index);
            task.markAsDone();
            return "Yee-haw! I've marked this task as done:\n" + task + "\n";
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }
}
