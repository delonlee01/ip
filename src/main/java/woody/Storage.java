package woody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

import command.Command;
import command.MarkCommand;
import command.TagCommand;
import exception.WoodyException;
import task.Task;
import task.TaskList;

/**
 * Represents a storage to read-write data locally in the chatbot system.
 */
public class Storage {
    private static final String DATA_PATH = Paths.get(".", "data", "woody.txt").toString();
    private final Parser parser;

    /**
     * Constructs a storage with the specified parser.
     *
     * @param parser Parser
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
     * Returns a TaskList object containing the tasks stored locally.
     *
     * @return TaskList
     * @throws WoodyException
     */
    public TaskList load() throws WoodyException {
        TaskList tasks = new TaskList();
        File data = new File(DATA_PATH);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(data));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] tokens = line.split("\\|");
                if (tokens.length != 3) {
                    continue;
                }

                Command command = parser.parseData(tokens[1]);
                if (command == null) { // Ignores malformed data in the local file
                    continue;
                }
                command.execute(tasks);

                int index = tasks.getTaskCount() - 1;
                if (!tokens[2].isBlank()) {
                    for (String label : tokens[2].split(" ")) {
                        new TagCommand(index, label.trim()).execute(tasks);
                    }
                }
                if (tokens[0].equals("1")) {
                    new MarkCommand(index).execute(tasks);
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
     * Stores all tasks in the specified TaskList object locally.
     *
     * @param taskList TaskList
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
