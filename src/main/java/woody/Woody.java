package woody;

import exception.WoodyException;

import task.TaskList;

import command.Command;

/**
 * Represents the chatbot system.
 */
public class Woody {
    private static Ui ui;
    private static Parser parser;
    private static Storage storage;
    private static TaskList taskList;

    public static void main(String[] args) {
        ui = new Ui();
        parser = new Parser();

        try {
            storage = new Storage(parser);
            taskList = storage.load();
        } catch (WoodyException e) {
            taskList = new TaskList();
            ui.writeOutput(e.toString());
        }

        ui.printWelcome();

        while (true) {
            try {
                String input = ui.readInput();
                Command command = parser.parseInput(input);
                if (command == null) {
                    throw new WoodyException("The command entered is invalid or formatted incorrectly.");
                }
                command.execute(taskList, ui);
                if (!command.isReadOnly()) {
                    storage.save(taskList);
                }
                if (command.isExit()) {
                    break;
                }
            } catch (WoodyException e) {
                ui.writeOutput(e.toString());
            }
        }
    }
}
