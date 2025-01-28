package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Paths;

import java.util.ArrayList;

import exception.InvalidArgumentsException;
import exception.TaskNotFoundException;
import exception.WoodyException;

public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String DATA_PATH = Paths.get(".", "data", "woody.txt").toString();

    public static void loadFromLocalStorage() throws WoodyException {
        File data = new File(DATA_PATH);
        try {
            if (!data.exists()) {
                data.getParentFile().mkdirs();
                data.createNewFile();
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(data));
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    String[] tokens = line.split("\\|");
                    Task task;
                    switch (tokens[0]) {
                    case "T":
                        task = new Todo(tokens[2]);
                        break;
                    case "D":
                        task = new Deadline(tokens[2]);
                        break;
                    case "E":
                        task = new Event(tokens[2]);
                        break;
                    default:
                        continue;
                    }
                    if (tokens[1].equals("1")) {
                        task.markAsDone();
                    } else {
                        task.markAsNotDone();
                    }
                    tasks.add(task);
                }
                reader.close();
            }
        } catch (FileNotFoundException e) {
            throw new WoodyException("Unable to find the local storage file.");
        } catch (IOException e) {
            throw new WoodyException("Unable to create the local storage file.");
        }
    }

    public static void writeToLocalStorage() throws WoodyException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_PATH));
            for (Task task : tasks) {
                writer.write(task.toDataString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new WoodyException("Unable to write to the local storage file.");
        }
    }

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s%n", i + 1, task);
        }
    }

    public static void printTasksByDate(String args) throws InvalidArgumentsException {
        LocalDate date;
        try {
            date = LocalDate.parse(args, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentsException("Date needs to be in the format: dd/MM/yyyy.");
        }
        System.out.printf("Here are the tasks for %s:%n",
                date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline && ((Deadline) task).by.equals(date)
                    || task instanceof Event && (((Event) task).from.equals(date) || ((Event) task).to.equals(date))) {
                System.out.printf("%d.%s%n", i + 1, task);
            }
        }
    }

    public static void insert(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
    }

    public static void delete(int idx) throws TaskNotFoundException {
        try {
            Task task = tasks.remove(idx);
            System.out.println("Got it. I've removed this task:\n" + task);
            System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    public static void markTaskAsDone(int idx) throws TaskNotFoundException {
        try {
            tasks.get(idx).markAsDone();
            System.out.println("Yee-haw! I've marked this task as done:");
            System.out.println(tasks.get(idx));
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    public static void markTaskAsNotDone(int idx) throws TaskNotFoundException {
        try {
            tasks.get(idx).markAsNotDone();
            System.out.println("Alright! I've marked this task as not done yet:");
            System.out.println(tasks.get(idx));
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }
}
