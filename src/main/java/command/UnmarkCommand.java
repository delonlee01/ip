package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.TaskNotFoundException;

import task.Task;
import task.TaskList;

import woody.Ui;

/**
 * Represents an unmark command in the chatbot system.
 */
public class UnmarkCommand extends Command {
    private static Pattern REGEX_PATTERN = Pattern.compile("^unmark (?<index>\\d+)$");
    private int index;

    /**
     * Constructs a command to mark the task at the specified index as not done.
     *
     * @param int index
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Returns an UnmarkCommand if the specified input matches the
     * usage format.
     *
     * @param String input
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
    public void execute(TaskList taskList, Ui ui) throws TaskNotFoundException {
        try {
            Task task = taskList.getTask(this.index);
            task.markAsNotDone();
            ui.writeOutput("Alright! I've marked this task as not done yet:");
            ui.writeOutput(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }
}
