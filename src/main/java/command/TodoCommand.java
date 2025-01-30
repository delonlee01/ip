package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task.TaskList;
import task.Todo;

import woody.Ui;

/**
 * Represents a todo command in the chatbot system.
 */
public class TodoCommand extends Command {
    private static final Pattern REGEX_PATTERN = Pattern.compile("^todo (?<description>.+)$");
    private String description;

    /**
     * Constructs a command to create a todo task.
     * 
     * @param String description
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Returns a <code>TodoCommand</code> if the specified input matches the
     * usage format.
     * 
     * @param String input
     * @return TodoCommand
     */
    public static TodoCommand createCommandIfValid(String input) {
        Matcher matcher = REGEX_PATTERN.matcher(input);
        if (matcher.matches()) {
            return new TodoCommand(matcher.group("description"));
        }
        return null;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Todo task = new Todo(this.description);
        taskList.addTask(task);
        ui.writeOutput("Got it. I've added this task:");
        ui.writeOutput(task.toString());
        ui.writeOutput(String.format("Now you have %d tasks in the list.", taskList.getTaskCount()));
    }
}
