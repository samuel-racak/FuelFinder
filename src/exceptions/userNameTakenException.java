package exceptions;

public class userNameTakenException extends Exception {
    public userNameTakenException() {
        super();
    }

    public userNameTakenException(String message) {
        super(message);
    }
}
