package exceptions;

/**
 * The userDoesNotExistException class represents an exception that is thrown
 * when a user does not exist.
 */
public class userDoesNotExistException extends Exception {
    /**
     * Constructor for the userDoesNotExistException class.
     * Initializes the exception with a default error message.
     */
    public userDoesNotExistException() {
        super();
    }

    /**
     * Constructor for the userDoesNotExistException class.
     * Initializes the exception with the given error message.
     *
     * @param message the error message
     */
    public userDoesNotExistException(String message) {
        super(message);
    }
}
