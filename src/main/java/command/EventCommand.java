package command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task.Event;
import task.TaskList;

import woody.Ui;

public class EventCommand extends Command {
    private static Pattern REGEX_PATTERN = Pattern.compile(
            "^event (?<description>.+) \\/from (?<from>[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}) \\/to (?<to>[0-9]{2}\\/[0-9]{2}\\/[0-9]{4})$");
    private String description;
    private LocalDate from;
    private LocalDate to;

    public EventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

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
    public void execute(TaskList taskList, Ui ui) {
        Event task = new Event(this.description, this.from, this.to);
        taskList.addTask(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.getTaskCount());
    }
}
