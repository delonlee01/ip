package woody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import exception.WoodyException;

/**
 * Represents a user interface to read inputs and print outputs in the chatbot
 * system.
 */
public class Ui {
    private static final String LOGO = """
             __        __              _      \s
             \\ \\      / /__   ___   __| |_   _\s
              \\ \\ /\\ / / _ \\ / _ \\ / _` | | | |
               \\ V  V / (_) | (_) | (_| | |_| |
                \\_/\\_/ \\___/ \\___/ \\__,_|\\__, |
                                         |___/
            """;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private boolean isSuppressed;

    /**
     * Constructs a new <code>Ui</code>.
     */
    public Ui() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new PrintWriter(System.out);
        this.isSuppressed = false;
    }

    /**
     * Sets the suppression status of the user interface. If true, outputs are not
     * printed.
     * 
     * @return Ui
     */
    public Ui toSuppressOutput() {
        this.isSuppressed = true;
        return this;
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcome() {
        if (isSuppressed) {
            return;
        }
        this.writer.print(LOGO);
        this.writer.println("Howdy partner! I'm Woody");
        this.writer.println("What can I do for you?");
        this.writer.flush();
    }

    /**
     * Reads user input from standard input.
     * 
     * @return String
     * @throws WoodyException
     */
    public String readInput() throws WoodyException {
        try {
            return this.reader.readLine();
        } catch (IOException e) {
            throw new WoodyException("Unable to read user's input.");
        }
    }

    /**
     * Prints output to standard out.
     */
    public void writeOutput(String text) {
        if (isSuppressed) {
            return;
        }
        this.writer.println(text);
        this.writer.flush();
    }
}
