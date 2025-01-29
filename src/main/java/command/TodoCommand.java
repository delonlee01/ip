package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task.TaskList;
import task.Todo;

import woody.Ui;

public class TodoCommand extends Command {
    private static final Pattern REGEX_PATTERN = Pattern.compile("^todo (?<description>.+)$");
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

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
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.getTaskCount());
    }
}
