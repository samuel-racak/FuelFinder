package user;

import java.time.LocalDate;

import exceptions.noPermissionException;
import exceptions.userDoesNotExistException;
import exceptions.userNameTakenException;
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
     * changes the username of the given user if the new username is not taken
     *
     * @param in User
     * @return true if username changed, false otherwise
     */
    public boolean changeUserName(String newUsername) {
        try {
            userManager.changeUserName(getCurrentUser(), newUsername);
            System.out.println("username: " + getCurrentUser().getUserName());
            for (User user : userManager.getRegisteredUsers().values()) {
                System.out.println(user.getUserName());
            }
        } catch (userDoesNotExistException | userNameTakenException e) {
            return false; // username not changed
        }
        return true; // username changed
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
        try {
            userManager.registerUser(UserType.BASIC_USER, userName, email, password, dateOfBirth, gender, null)
        } catch (userNameTakenException e) {
            return false; // user not registered
        }
        session.setCurrentUser(userManager.getUser(userName));
        return true;

        // if (userManager.registerUser(UserType.BASIC_USER, userName, email, password, dateOfBirth, gender,
        //         null) == null) {
        //     return false;
        // }
        // session.setCurrentUser(userManager.getUser(userName));
        // return true;
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
     * @throws wrongCardDetailsException
     * @return true if registration successful, false otherwise
     */
    public boolean registerPremiumUser(String userName, String email, String password, LocalDate dateOfBirth,
            String gender, String creditCardNumber, String creditCardExpirationDate, String creditCardSecurityCode)
            throws wrongCardDetailsException {

        // let it throw exception if card details are wrong to handle it in the gui by
        // showing a message
        PaymentCard card = new PaymentCard(creditCardNumber, creditCardExpirationDate, creditCardSecurityCode);

        try {
            userManager.registerPremiumUser(UserType.PREMIUM_USER, userName, email, password, dateOfBirth, gender, null,
                    card);
        } catch (userNameTakenException e) {
            return false; // user not registered
        }

        session.setCurrentUser(userManager.getUser(userName));
        return true;
    }

    /**
     * deletes the given user
     *
     * @param in User to be deleted
     * @return true if user deleted, false otherwise
     */
    public boolean deleteUser(User in) {
        try {
            userManager.deleteUser(in, getCurrentUser());
            return true;
        } catch (noPermissionException e) {
            e.printStackTrace();
            return false;
        } catch (userDoesNotExistException e) {
            return false;
        }
    }

    public boolean deleteUser(User in, User admin) throws noPermissionException {
        if (admin.getUserType() != UserType.ADMIN) {
            throw new noPermissionException();
        }
        try {
            userManager.deleteUser(in);
        } catch (userDoesNotExistException e) {
            return false;
        }
        return true;
    }
}
