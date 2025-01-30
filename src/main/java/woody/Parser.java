package woody;

import java.util.List;
import exception.WoodyException;

import command.ByeCommand;
import command.CheckCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;

public class Parser {
    private final List<Class<? extends Command>> ALLOWED_COMMANDS = List.of(ListCommand.class, CheckCommand.class,
            TodoCommand.class, DeadlineCommand.class, EventCommand.class, MarkCommand.class, UnmarkCommand.class,
            DeleteCommand.class, ByeCommand.class, FindCommand.class);

    private Command parse(String text, List<Class<? extends Command>> commands) throws WoodyException {
        Class<?>[] params = new Class[]{String.class};
        Object[] args = new Object[]{text};
        for (var commandClass : commands) {
            try {
                Command command = (Command) commandClass.getMethod("createCommandIfValid", params).invoke(null, args);
                if (command != null) {
                    return command;
                }
            } catch (Exception e) {
                throw new WoodyException("Unable to parse the command given.");
            }
        }
        return null;
    }

    public Command parseInput(String text) throws WoodyException {
        return parse(text, ALLOWED_COMMANDS);
    }

    public Command parseData(String text) throws WoodyException {
        List<Class<? extends Command>> taskCommands = List.of(TodoCommand.class, DeadlineCommand.class,
                EventCommand.class);
        return parse(text, taskCommands);
    }
}
