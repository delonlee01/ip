package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task.TaskList;
import task.Todo;

/**
 * Represents a todo command in the chatbot system.
 */
public class TodoCommand extends Command {
    private static final Pattern REGEX_PATTERN = Pattern.compile("^todo (?<description>.+)$");
    private final String description;

    /**
     * Constructs a command to create a todo task.
     *
     * @param description description of task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Returns a TodoCommand if the specified input matches the usage format.
     *
     * @param input string representation of command
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
    public String execute(TaskList taskList) {
        Todo task = new Todo(this.description);
        taskList.addTask(task);
        String output = "Got it. I've added this task:\n";
        output += task + "\n";
        output += String.format("Now you have %d tasks in the list.\n", taskList.getTaskCount());
        return output;
    }
}
