package woody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.Paths;

import exception.WoodyException;

import task.Task;
import task.TaskList;

import command.Command;
import command.MarkCommand;

/**
 * Represents a storage to read-write data locally in the chatbot system.
 */
public class Storage {
    private static final String DATA_PATH = Paths.get(".", "data", "woody.txt").toString();
    private Parser parser;

    /**
     * Constructs a storage with the specified parser.
     * 
     * @param Parser parser
     * @throws WoodyException
     */
    public Storage(Parser parser) throws WoodyException {
        this.parser = parser;
        File data = new File(DATA_PATH);
        try {
            if (!data.exists()) {
                data.getParentFile().mkdirs();
                data.createNewFile();
            }
        } catch (IOException e) {
            throw new WoodyException("Unable to create the local storage file.");
        }
    }

    /**
     * Returns a <code>TaskList</code> object containing the tasks stored locally.
     * 
     * @return TaskList
     * @throws WoodyException
     */
    public TaskList load() throws WoodyException {
        TaskList tasks = new TaskList();
        File data = new File(DATA_PATH);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(data));
            Ui suppressedUi = new Ui().toSuppressOutput(); // does not suppress errors
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] tokens = line.split("\\|");
                Command command = parser.parseData(tokens[1]);
                if (command == null) { // malformed data in local file
                    continue;
                }
                command.execute(tasks, suppressedUi);
                if (tokens[0].equals("1")) {
                    new MarkCommand(tasks.getTaskCount() - 1).execute(tasks, suppressedUi);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new WoodyException("Unable to find the local storage file.");
        } catch (IOException e) {
            throw new WoodyException("Unable to read from the local storage file.");
        }
        return tasks;
    }

    /**
     * Stores all tasks in the specified <code>TaskList</code> object locally.
     * 
     * @param TaskList taskList
     * @throws WoodyException
     */
    public void save(TaskList taskList) throws WoodyException {
        File data = new File(DATA_PATH);
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(data));
            for (Task task : taskList.getTasks()) {
                writer.write(task.toDataString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new WoodyException("Unable to write to the local storage file.");
        }
    }
}
