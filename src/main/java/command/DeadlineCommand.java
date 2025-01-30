package command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task.Deadline;
import task.TaskList;
import woody.Ui;

/**
 * Represents a deadline command in the chatbot system.
 */
public class DeadlineCommand extends Command {
    private static final Pattern REGEX_PATTERN = Pattern
            .compile("^deadline (?<description>.+) /by (?<by>[0-9]{2}/[0-9]{2}/[0-9]{4})$");
    private final String description;
    private final LocalDate by;

    /**
     * Constructs a command to create a deadline task.
     *
     * @param description description of task
     * @param by          due date
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Returns a DeadlineCommand if the specified input matches the
     * usage format.
     *
     * @param input string representation of command
     * @return DeadlineCommand
     */
    public static DeadlineCommand createCommandIfValid(String input) {
        Matcher matcher = REGEX_PATTERN.matcher(input);
        if (matcher.matches()) {
            LocalDate by = LocalDate.parse(matcher.group("by"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return new DeadlineCommand(matcher.group("description"), by);
        }
        return null;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Deadline task = new Deadline(this.description, this.by);
        taskList.addTask(task);
        ui.writeOutput("Got it. I've added this task:");
        ui.writeOutput(task.toString());
        ui.writeOutput(String.format("Now you have %d tasks in the list.", taskList.getTaskCount()));
    }
}
