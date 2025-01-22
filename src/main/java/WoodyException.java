public class WoodyException extends Exception {
    public WoodyException(String msg) {
        super("Something went wrong partner. " + msg);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
