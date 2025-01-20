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
        System.out.println("Hello! I'm Woody");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String userCmd = sc.nextLine();
            if (userCmd.equals("bye")) {
                break;
            }
            System.out.println(userCmd);
        }

        System.out.println("Bye! Hope to see you again soon!");
        sc.close();
    }
}
