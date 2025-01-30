package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.TaskNotFoundException;
import task.Task;
import task.TaskList;
import woody.Ui;

public class MarkCommand extends Command {
    private static final Pattern REGEX_PATTERN = Pattern.compile("^mark (?<index>\\d+)$");
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

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
