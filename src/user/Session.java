package user;

// this class is used to keep track of the current user
public class Session {
    private static Session instance;
    private static User currentUser; // there can only be one user logged in at a time

    private Session() {
        currentUser = null;
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}