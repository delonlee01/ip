package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.TaskNotFoundException;

import task.Task;
import task.TaskList;

import woody.Ui;

/**
 * Represents a mark command in the chatbot system.
 */
public class MarkCommand extends Command {
    private static Pattern REGEX_PATTERN = Pattern.compile("^mark (?<index>\\d+)$");
    private int index;

    /**
     * Constructs a command to mark the task at the specified index as done.
     *
     * @param int index
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Returns a MarkCommand if the specified input matches the usage
     * format.
     *
     * @param String input
     * @return MarkCommand
     */
    public static MarkCommand createCommandIfValid(String input) {
        Matcher matcher = REGEX_PATTERN.matcher(input);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group("index")) - 1;
            return new MarkCommand(index);
        }
        return null;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskNotFoundException {
        try {
            Task task = taskList.getTask(this.index);
            task.markAsDone();
            ui.writeOutput("Yee-haw! I've marked this task as done:");
            ui.writeOutput(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }
}
