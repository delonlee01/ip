package command;

import task.TaskList;
import woody.Ui;

/**
 * Represents a bye command in the chatbot system.
 */
public class ByeCommand extends Command {
    private static final String REGEX_PATTERN = "^bye$";

    /**
     * Returns a ByeCommand if the specified input matches the usage
     * format.
     *
     * @param input string representation of command
     * @return ByeCommand
     */
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
