package exceptions;

/**
 * The userNameTakenException class represents an exception that is thrown when
 * a user name is already taken.
 */
public class userNameTakenException extends Exception {
    /**
     * Constructor for the userNameTakenException class.
     * Initializes the exception with a default error message.
     */
    public userNameTakenException() {
        super();
    }

    /**
     * Constructor for the userNameTakenException class.
     * Initializes the exception with the given error message.
     *
     * @param message the error message
     */
    public userNameTakenException(String message) {
        super(message);
    }
}
