package exceptions;

public class userDoesNotExistException extends Exception {
    public userDoesNotExistException() {
        super();
    }

    public userDoesNotExistException(String message) {
        super(message);
    }
}
