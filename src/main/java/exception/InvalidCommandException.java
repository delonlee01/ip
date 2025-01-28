package exception;

public class InvalidCommandException extends WoodyException {
    public InvalidCommandException(String command) {
        super(String.format("\"%s\" is not a recognized command.", command));
    }
}
