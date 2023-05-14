package exceptions;

/**
 * The noPermissionException class represents an exception that is thrown when a
 * user does not have permission to access a page.
 */
public class noPermissionException extends Exception {
    /**
     * Constructor for the noPermissionException class.
     * Initializes the exception with a default error message.
     */
    public noPermissionException() {
        super("You do not have permission to access this page");
    }

    /**
     * Constructor for the noPermissionException class.
     * Initializes the exception with the given error message.
     *
     * @param errorMessage the error message
     */
    public noPermissionException(String errorMessage) {
        super(errorMessage);
    }
}
