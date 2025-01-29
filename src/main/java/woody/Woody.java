package woody;

import exception.WoodyException;

import task.TaskList;

import command.Command;

public class Woody {
    private static Ui ui;
    private static Parser parser;
    private static TaskList taskList;

    public static void main(String[] args) {
        ui = new Ui();
        parser = new Parser();
        taskList = new TaskList();

        ui.printWelcome();

        while (true) {
            try {
                String input = ui.readInput();
                Command command = parser.parse(input);
                if (command == null) {
                    throw new WoodyException("The command entered is not valid or formatted correctly.");
                }
                command.execute(taskList, ui);
                if (command.isExit()) {
                    break;
                }
            } catch (WoodyException e) {
                ui.writeOutput(e.toString());
            }
        }
    }
}
