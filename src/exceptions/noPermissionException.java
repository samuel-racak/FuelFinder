package exceptions;

public class noPermissionException extends Exception {
    public noPermissionException() {
        super("You do not have permission to access this page");
    }

    public noPermissionException(String errorMessage) {
        super(errorMessage);
    }
}
