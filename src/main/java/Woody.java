import java.util.Scanner;

public class Woody {
    private static Task[] tasks = new Task[100];
    private static int idx = 0;

    private static void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.length; i++) {
            Task task = tasks[i];
            if (task == null) {
                break;
            }
            System.out.println(String.format("%d.%s", i + 1, task));
        }
    }

    private static void addTask(Task task) {
        tasks[idx++] = task;
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println(String.format("Now you have %d tasks in the list.", idx));
    }

    private static void mark(int taskIdx) {
        tasks[taskIdx].markAsDone();
        System.out.println("Yee-haw! I've marked this task as done:");
        System.out.println(tasks[taskIdx]);
    }

    private static void unmark(int taskIdx) {
        tasks[taskIdx].markAsNotDone();
        System.out.println("Alright! I've marked this task as not done yet:");
        System.out.println(tasks[taskIdx]);
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
            if (action.equals("bye")) {
                break;
            }
            if (action.equals("list")) {
                displayTasks();
            } else if (action.startsWith("mark")) {
                mark(Integer.parseInt(sc.next()) - 1);
            } else if (action.startsWith("unmark")) {
                unmark(Integer.parseInt(sc.next()) - 1);
            } else if (action.startsWith("todo")) {
                Todo todo = new Todo(sc.nextLine().strip());
                addTask(todo);
            } else if (action.startsWith("deadline")) {
                String[] tokens = sc.nextLine().split(" /by ");
                Deadline deadline = new Deadline(tokens[0].strip(), tokens[1].strip());
                addTask(deadline);
            } else if (action.startsWith("event")) {
                String[] tokens = sc.nextLine().split("(\\/from|\\/to)");
                Event event = new Event(tokens[0].strip(), tokens[1].strip(), tokens[2].strip());
                addTask(event);
            } else {
                continue;
            }
        }

        System.out.println("Bye! See you soon partner!");
        sc.close();
    }
}
