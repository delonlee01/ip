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
        String[] history = new String[100];
        int idx = 0;
        while (true) {
            String userCmd = sc.nextLine();
            if (userCmd.equals("bye")) {
                break;
            }
            if (userCmd.equals("list")) {
                for (int i = 0; i < history.length; i++) {
                    String item = history[i];
                    if (item != null) {
                        System.out.println(String.format("%d. %s", i + 1, history[i]));
                    }
                }
            } else {
                history[idx++] = userCmd;
                System.out.println("added: " + userCmd);
            }
        }

        System.out.println("Bye! See you soon partner!");
        sc.close();
    }
}
