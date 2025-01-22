import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Woody {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final List<String> ALLOWED_COMMANDS = Arrays.asList("list", "todo", "deadline", "event", "mark",
            "unmark", "delete", "bye");

    private static void checkIfDetailsIsEmpty(String details) throws InvalidArgumentsException {
        if (details.isBlank()) {
            throw new InvalidArgumentsException("The details cannot be empty.");
        }
    }

    private static void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s%n", i + 1, task);
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
    }

    private static void deleteTask(String args) throws InvalidArgumentsException {
        checkIfDetailsIsEmpty(args);
        int taskIdx = -1;
        try {
            taskIdx = Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException("\"delete\" requires an item number.");
        }
        Task task = tasks.remove(taskIdx);
        System.out.println("Got it. I've removed this task:\n" + task);
        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
    }

    private static void setTaskStatus(String args, boolean isDone) throws InvalidArgumentsException {
        checkIfDetailsIsEmpty(args);
        int taskIdx = -1;
        try {
            taskIdx = Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException("\"mark\"/\"unmark\" requires an item number.");
        }
        if (isDone) {
            tasks.get(taskIdx).markAsDone();
            System.out.println("Yee-haw! I've marked this task as done:");
        } else {
            tasks.get(taskIdx).markAsNotDone();
            System.out.println("Alright! I've marked this task as not done yet:");
        }
        System.out.println(tasks.get(taskIdx));
    }

    public static void main(String[] args) {
        String logo = """
                 __        __              _      \s
                 \\ \\      / /__   ___   __| |_   _\s
                  \\ \\ /\\ / / _ \\ / _ \\ / _` | | | |
                   \\ V  V / (_) | (_) | (_| | |_| |
                    \\_/\\_/ \\___/ \\___/ \\__,_|\\__, |
                                             |___/
                """;
        System.out.print(logo);
        System.out.println("Howdy partner! I'm Woody");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String action = sc.next();
            try {
                String actionArgs = sc.nextLine().strip();
                if (!ALLOWED_COMMANDS.contains(action)) {
                    throw new InvalidCommandException(action);
                }
                if (action.equals("bye")) {
                    break;
                }
                if (action.equals("list")) {
                    displayTasks();
                }
                if (action.equals("todo")) {
                    checkIfDetailsIsEmpty(actionArgs);
                    Todo task = new Todo(actionArgs);
                    addTask(task);
                }
                if (action.equals("deadline")) {
                    checkIfDetailsIsEmpty(actionArgs);
                    String[] tokens = actionArgs.split(" /by ");
                    if (tokens.length != 2) {
                        throw new InvalidArgumentsException("\"deadline\" requires a description, and by.");
                    }
                    Deadline task = new Deadline(tokens[0], tokens[1]);
                    addTask(task);
                }
                if (action.equals("event")) {
                    checkIfDetailsIsEmpty(actionArgs);
                    String[] tokens = actionArgs.split("(/from|/to)");
                    if (tokens.length != 3) {
                        throw new InvalidArgumentsException("\"event\" requires a description, from, and to.");
                    }
                    Event task = new Event(tokens[0].strip(), tokens[1].strip(), tokens[2].strip());
                    addTask(task);
                }
                if (action.equals("mark")) {
                    setTaskStatus(actionArgs, true);
                }
                if (action.equals("unmark")) {
                    setTaskStatus(actionArgs, false);
                }
                if (action.equals("delete")) {
                    deleteTask(actionArgs);
                }
            } catch (WoodyException e) {
                System.out.println(e);
            }
        }

        System.out.println("Bye! See you soon partner!");
        sc.close();
    }
}
