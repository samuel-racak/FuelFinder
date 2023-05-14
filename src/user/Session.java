package user;

/**
 * The Session class represents a session in the application.
 * It is a singleton class, meaning there can only be one instance of it.
 */
public class Session {
    private static Session instance;
    private static User currentUser; // there can only be one user logged in at a time

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private Session() {
        currentUser = null;
    }

    /**
     * Returns the instance of the Session class.
     * If the instance does not exist, it is created.
     *
     * @return the instance of the Session class
     */
    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    /**
     * Sets the current user of the session.
     *
     * @param user the user to set as the current user
     */
    public synchronized void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Returns the current user of the session.
     *
     * @return the current user of the session
     */
    public User getCurrentUser() {
        return currentUser;
    }
}
