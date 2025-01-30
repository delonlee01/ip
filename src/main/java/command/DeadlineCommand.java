package command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task.Deadline;
import task.TaskList;
import woody.Ui;

public class DeadlineCommand extends Command {
    private static Pattern REGEX_PATTERN = Pattern
            .compile("^deadline (?<description>.+) \\/by (?<by>[0-9]{2}\\/[0-9]{2}\\/[0-9]{4})$");
    private String description;
    private LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

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
