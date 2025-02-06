package woody;

import command.Command;
import exception.WoodyException;
import task.TaskList;

/**
 * Represents the chatbot system.
 */
public class Woody {
    private final Parser parser;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a Woody object.
     */
    public Woody() {
        this.parser = new Parser();
        try {
            this.storage = new Storage(parser);
            this.taskList = storage.load();
        } catch (WoodyException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Returns Woody's response based on the given user input.
     *
     * @param userInput
     * @return Woody's response
     */
    public String getResponse(String userInput) {
        try {
            Command command = this.parser.parseInput(userInput);
            if (command == null) {
                throw new WoodyException("The command entered is invalid or formatted incorrectly.");
            }
            String response = command.execute(this.taskList);
            if (!command.isReadOnly()) {
                this.storage.save(this.taskList);
            }
            return response;
        } catch (WoodyException e) {
            return e.toString();
        }
    }
}
