package exceptions;

/**
 * The wrongCardDetailsException class represents an exception that is thrown
 * when the card details entered are incorrect.
 */
public class wrongCardDetailsException extends Exception {
    /**
     * Constructor for the wrongCardDetailsException class.
     * Initializes the exception with a default error message.
     */
    public wrongCardDetailsException() {
        super("Wrong card details");
    }
}
