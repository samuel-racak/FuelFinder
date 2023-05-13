package user;

import java.time.LocalDate;

import exceptions.wrongCardDetailsException;

public class SessionManager {
    private static SessionManager instance;
    private UserManager userManager;
    private Session session;

    private SessionManager() {
        userManager = UserManager.getInstance();
        session = Session.getInstance();
    }

    /**
     * returns the instance of the SessionManager
     *
     * @return
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    /**
     * sets the current user in session to the user with the given username and
     * password
     *
     * @param username string
     * @param password string
     * @return true if login successful, false otherwise
     */
    public boolean login(String username, String password) {
        User user = userManager.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setCurrentUser(user);
            return true;
        }
        return false;
    }

    /**
     * sets the current user in session to null
     *
     * @param user User
     */
    public void logout() {
        session.setCurrentUser(null);
    }

    /**
     * checks if the current user in session is logged in
     *
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return session.getCurrentUser() != null;
    }

    /**
     * checks if the current user in session is an admin
     *
     * @return true if admin, false otherwise
     */
    public boolean isAdmin() {
        return session.getCurrentUser().getUserType() == UserType.ADMIN;
    }

    /**
     * returns currently logged in user
     *
     * @return User
     */
    public User getCurrentUser() {
        return session.getCurrentUser();
    }

    /**
     * returns the username of the currently logged in user
     *
     * @return String
     */
    public String getCurrentUsername() {
        return session.getCurrentUser().getUserName();
    }

    /**
     * registers a new user with the given parameters
     *
     * @param userName
     * @param email
     * @param password
     * @param dateOfBirth
     * @param gender
     * @return
     */
    public boolean registerUser(String userName, String email, String password, LocalDate dateOfBirth, String gender) {
        // User in = new User(UserType.BASIC_USER, userName, email, password,
        // dateOfBirth, gender, null); // user will not
        // have a car
        // right away
        if (userManager.registerUser(UserType.BASIC_USER, userName, email, password, dateOfBirth, gender,
                null) == null) {
            return false;
        }
        session.setCurrentUser(userManager.getUser(userName));
        return true;
    }

    /**
     * registers a new premium user with the given parameters
     *
     * @param userName
     * @param email
     * @param password
     * @param dateOfBirth
     * @param gender
     * @param creditCardNumber
     * @param creditCardExpirationDate
     * @param creditCardSecurityCode
     * @return
     * @throws wrongCardDetailsException
     */
    public boolean registerPremiumUser(String userName, String email, String password, LocalDate dateOfBirth,
            String gender, String creditCardNumber, String creditCardExpirationDate, String creditCardSecurityCode)
            throws wrongCardDetailsException {

        // PremiumUser in = new PremiumUser(UserType.PREMIUM_USER, userName, email,
        // password, dateOfBirth, gender, null,
        // new PaymentCard(creditCardNumber, creditCardExpirationDate,
        // creditCardSecurityCode)); // user will not
        // have a car
        // right away
        PaymentCard card = new PaymentCard(creditCardNumber, creditCardExpirationDate, creditCardSecurityCode);

        if (userManager.registerPremiumUser(UserType.PREMIUM_USER, userName, email, password, dateOfBirth, gender, null,
                card) == null) {
            throw new IllegalArgumentException("User already exists");
        }
        session.setCurrentUser(userManager.getUser(userName));
        return true;
    }
    // public User deleteUser(User in, User admin) throws noPermissionException {
    // return userManager.deleteUser(in, admin);
    // }

}
