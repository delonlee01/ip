package exception;

/**
 * Represents an exception that is thrown within the chatbot system.
 */
public class WoodyException extends Exception {
    /**
     * Constructs a new WoodyException with the message specified.
     *
     * @param String msg
     */
    public WoodyException(String msg) {
        super("Something went wrong partner. " + msg);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
