package command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task.Event;
import task.TaskList;

/**
 * Represents an event command in the chatbot system.
 */
public class EventCommand extends Command {
    private static final Pattern REGEX_PATTERN = Pattern.compile(
            "^event (?<description>.+) /from (?<from>[0-9]{2}/[0-9]{2}/[0-9]{4})"
                    + " /to (?<to>[0-9]{2}/[0-9]{2}/[0-9]{4})$");
    private final String description;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs a command to create an event task.
     *
     * @param description description of task
     * @param from        start date
     * @param to          end date
     */
    public EventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a EventCommand if the specified input matches the usage format.
     *
     * @param input string representation of command
     * @return EventCommand
     */
    public static EventCommand createCommandIfValid(String input) {
        Matcher matcher = REGEX_PATTERN.matcher(input);
        if (matcher.matches()) {
            LocalDate from = LocalDate.parse(matcher.group("from"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate to = LocalDate.parse(matcher.group("to"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return new EventCommand(matcher.group("description"), from, to);
        }
        return null;
    }

    @Override
    public String execute(TaskList taskList) {
        Event task = new Event(this.description, this.from, this.to);
        taskList.addTask(task);
        String output = "Got it. I've added this task:\n";
        output += task + "\n";
        output += String.format("Now you have %d tasks in the list.\n", taskList.getTaskCount());
        return output;
    }
}
