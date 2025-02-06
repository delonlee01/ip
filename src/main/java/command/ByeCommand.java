package command;

import task.TaskList;

/**
 * Represents a bye command in the chatbot system.
 */
public class ByeCommand extends Command {
    private static final String REGEX_PATTERN = "^bye$";

    /**
     * Returns a ByeCommand if the specified input matches the usage format.
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
    public String execute(TaskList taskList) {
        return "Bye! See you soon partner!";
    }
}
