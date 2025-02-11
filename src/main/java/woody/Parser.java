package woody;

import java.util.List;

import command.ByeCommand;
import command.CheckCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TagCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exception.WoodyException;

/**
 * Represents a parser to interpret user input in the chatbot system.
 */
public class Parser {
    private static final List<Class<? extends Command>> ALLOWED_COMMANDS = List.of(ListCommand.class,
            CheckCommand.class, TodoCommand.class, DeadlineCommand.class, EventCommand.class, MarkCommand.class,
            UnmarkCommand.class, DeleteCommand.class, ByeCommand.class, FindCommand.class, TagCommand.class);

    /**
     * Returns a command if the text matches the usage format of a command in the
     * given list.
     *
     * @param text     string representation of command
     * @param commands List&lt;? extends Commands&gt;
     * @return Command
     * @throws WoodyException
     */
    private Command parse(String text, List<Class<? extends Command>> commands) throws WoodyException {
        Class<?>[] params = new Class[]{String.class};
        Object[] args = new Object[]{text};
        for (var commandClass : commands) {
            try {
                Command command = (Command) commandClass.getMethod("createCommandIfValid", params).invoke(null, args);
                if (command == null) {
                    continue;
                }
                return command;
            } catch (Exception e) {
                throw new WoodyException("Unable to parse the command given.");
            }
        }
        return null;
    }

    /**
     * Returns a command if the user input matches the usage format of a command.
     *
     * @param text string representation of command
     * @return Command
     * @throws WoodyException
     */
    public Command parseInput(String text) throws WoodyException {
        return parse(text, ALLOWED_COMMANDS);
    }

    /**
     * Returns a command if the data record matches the usage format of a command.
     *
     * @param text string representation of command
     * @return Command
     * @throws WoodyException
     */
    public Command parseData(String text) throws WoodyException {
        List<Class<? extends Command>> taskCommands = List.of(TodoCommand.class, DeadlineCommand.class,
                EventCommand.class);
        return parse(text, taskCommands);
    }
}
