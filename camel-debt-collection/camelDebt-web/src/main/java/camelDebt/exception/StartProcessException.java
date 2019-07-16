package camelDebt.exception;

public class StartProcessException extends Exception {
    public StartProcessException(String message) {
        super(message);
    }

    public StartProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
