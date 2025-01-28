import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import exception.InvalidArgumentsException;
import exception.InvalidCommandException;
import exception.WoodyException;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

public class Woody {
    private static final List<String> ALLOWED_COMMANDS = Arrays.asList("list", "todo", "deadline", "event", "mark",
            "unmark", "delete", "bye");

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
                    TaskList.printTasks();
                    continue;
                }
                if (actionArgs.isBlank()) {
                    throw new InvalidArgumentsException("The details cannot be empty.");
                }
                if (action.equals("todo")) {
                    TaskList.insert(new Todo(actionArgs));
                    continue;
                }
                if (action.equals("deadline")) {
                    TaskList.insert(new Deadline(actionArgs));
                    continue;
                }
                if (action.equals("event")) {
                    TaskList.insert(new Event(actionArgs));
                    continue;
                }
                if (!actionArgs.matches("^[0-9]+$")) {
                    throw new InvalidArgumentsException(String.format("\"%s\" requires an item number.", action));
                }
                int idx = Integer.parseInt(actionArgs) - 1;
                if (action.equals("mark")) {
                    TaskList.markTaskAsDone(idx);
                    continue;
                }
                if (action.equals("unmark")) {
                    TaskList.markTaskAsNotDone(idx);
                    continue;
                }
                if (action.equals("delete")) {
                    TaskList.delete(idx);
                    continue;
                }
            } catch (WoodyException e) {
                System.out.println(e);
            }
        }

        System.out.println("Bye! See you soon partner!");
        sc.close();
    }
}
