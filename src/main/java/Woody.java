import java.util.Scanner;

public class Woody {
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
        Task[] tasks = new Task[100];
        int idx = 0;
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.length; i++) {
                    Task task = tasks[i];
                    if (task == null) {
                        break;
                    }
                    System.out.println(String.format("%d.[%s] %s", i + 1, task.getStatusIcon(), task));
                }
            } else if (userInput.startsWith("mark")) {
                String[] tokens = userInput.split(" ");
                int taskIdx = Integer.parseInt(tokens[1]) - 1;
                tasks[taskIdx].markAsDone();
                System.out.println("Yee-haw! I've marked this task as done:");
                System.out.println("[X] " + tasks[taskIdx]);
            } else if (userInput.startsWith("unmark")) {
                String[] tokens = userInput.split(" ");
                int taskIdx = Integer.parseInt(tokens[1]) - 1;
                tasks[taskIdx].markAsNotDone();
                System.out.println("Alright! I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks[taskIdx]);
            } else {
                Task task = new Task(userInput);
                tasks[idx++] = task;
                System.out.println("added: " + task);
            }
        }

        System.out.println("Bye! See you soon partner!");
        sc.close();
    }
}
