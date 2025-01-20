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
        @SuppressWarnings("unchecked")
        Pair<String, Boolean>[] tasks = new Pair[100];
        int idx = 0;
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.length; i++) {
                    Pair<String, Boolean> item = tasks[i];
                    if (item == null) {
                        break;
                    }
                    String status = item.getSecond() ? "X" : " ";
                    System.out.println(String.format("%d.[%s] %s", i + 1, status, item.getFirst()));
                }
            } else if (userInput.startsWith("mark")) {
                String[] tokens = userInput.split(" ");
                int itemIdx = Integer.parseInt(tokens[1]) - 1;
                tasks[itemIdx].setSecond(true);
                System.out.println("Yee-haw! I've marked this task as done:");
                System.out.println("[X] " + tasks[itemIdx].getFirst());
            } else if (userInput.startsWith("unmark")) {
                String[] tokens = userInput.split(" ");
                int itemIdx = Integer.parseInt(tokens[1]) - 1;
                tasks[itemIdx].setSecond(false);
                System.out.println("Alright! I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks[itemIdx].getFirst());
            } else {
                tasks[idx++] = new Pair<String, Boolean>(userInput, false);
                System.out.println("added: " + userInput);
            }
        }

        System.out.println("Bye! See you soon partner!");
        sc.close();
    }
}
