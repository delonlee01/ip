package command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task.Task;
import task.TaskList;

/**
 * Represents a find command in the chatbot system.
 */
public class FindCommand extends Command {
    private static final Pattern REGEX_PATTERN = Pattern.compile("^find (?<keyword>.+)$");
    private final String keyword;

    /**
     * Constructs a command to find tasks with descriptions that contains the
     * specified keyword.
     *
     * @param keyword word of interest
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns a FindCommand if the specified input matches the usage format.
     *
     * @param input string representation of command
     * @return FindCommand
     */
    public static FindCommand createCommandIfValid(String input) {
        Matcher matcher = REGEX_PATTERN.matcher(input);
        if (matcher.matches()) {
            return new FindCommand(matcher.group("keyword"));
        }
        return null;
    }

    @Override
    public String execute(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (!task.getDescription().contains(keyword)) {
                continue;
            }
            output.append(String.format("%d.%s\n", i + 1, task));
        }
        return output.toString();
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }
}
