package command;

import task.TaskList;

import woody.Ui;

public class ByeCommand extends Command {
    private static String REGEX_PATTERN = "^bye$";

    public static boolean isValid(String input) {
        return input.matches(ByeCommand.REGEX_PATTERN);
    }

    public static ByeCommand createCommandIfValid(String input) {
        if (input.matches(ByeCommand.REGEX_PATTERN)) {
            return new ByeCommand();
        }
        return null;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.writeOutput("Bye! See you soon partner!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
