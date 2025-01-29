package command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

import woody.Ui;

public class CheckCommand extends Command {
    private static Pattern REGEX_PATTERN = Pattern.compile("^check (?<date>[0-9]{2}\\/[0-9]{2}\\/[0-9]{4})$");
    private LocalDate date;

    public CheckCommand(LocalDate date) {
        this.date = date;
    }

    public static CheckCommand createCommandIfValid(String input) {
        Matcher matcher = REGEX_PATTERN.matcher(input);
        if (matcher.matches()) {
            LocalDate date = LocalDate.parse(matcher.group("date"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return new CheckCommand(date);
        }
        return null;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ArrayList<Task> tasks = taskList.getTasks();
        ui.writeOutput(
                String.format("Here are the tasks for %s:", date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))));
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (!deadline.getBy().equals(date)) {
                    continue;
                }
            }
            if (task instanceof Event) {
                Event event = (Event) task;
                if (!event.getFrom().equals(date) && !event.getTo().equals(date)) {
                    continue;
                }
            }
            if (task instanceof Todo) {
                continue;
            }
            ui.writeOutput(String.format("%d.%s", i + 1, task));
        }
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }
}
