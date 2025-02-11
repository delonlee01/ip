package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.TaskNotFoundException;
import task.Tag;
import task.Task;
import task.TaskList;

/**
 * Represents a tag command in the chatbot system.
 */
public class TagCommand extends Command {
    private static final Pattern REGEX_PATTERN = Pattern.compile("^tag (?<index>\\d) (?<label>\\w+)$");
    private final int index;
    private final Tag tag;

    /**
     * Constructs a command to tag the task at the specified index.
     *
     * @param index index of task
     * @param label label of tag
     */
    public TagCommand(int index, String label) {
        this.index = index;
        this.tag = new Tag(label);
    }

    /**
     * Returns a TagCommand if the specified input matches the usage format.
     *
     * @param input string representation of command
     * @return TagCommand
     */
    public static TagCommand createCommandIfValid(String input) {
        Matcher matcher = REGEX_PATTERN.matcher(input);
        if (!matcher.matches()) {
            return null;
        }
        int index = Integer.parseInt(matcher.group("index")) - 1;
        String label = matcher.group("label");
        return new TagCommand(index, label);
    }

    @Override
    public String execute(TaskList tasks) throws TaskNotFoundException {
        try {
            Task task = tasks.getTask(index);
            task.addTag(this.tag);
            return "Alright! I've tagged this task:\n" + task + "\n";
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }
}
