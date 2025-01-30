package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.TaskNotFoundException;
import task.Task;
import task.TaskList;
import woody.Ui;

public class UnmarkCommand extends Command {
    private static Pattern REGEX_PATTERN = Pattern.compile("^unmark (?<index>\\d+)$");
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

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
