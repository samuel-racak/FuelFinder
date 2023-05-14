package managers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import car.Car;
import car.FuelType;
import exceptions.noPermissionException;
import exceptions.userDoesNotExistException;
import exceptions.userNameTakenException;
import exceptions.wrongCardDetailsException;
import location.Location;
import user.PaymentCard;
import user.PremiumUser;
import user.User;
import user.UserType;

public class UserManager {
    private static UserManager instance;
    private static Map<String, User> registeredUsers; // first argument is the username and the second is the user
                                                      // object

    private UserManager() {
        registeredUsers = new HashMap<>();
    }

    /**
     * loads the registered users from the given file and return user manager object
     *
     * @param fileName
     * @return UserManager with the loaded users
     */
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
            registeredUsers = UserManager.loadFromFile("src/resources/users.ser");
            if (registeredUsers == null) {
                registeredUsers = new HashMap<>();
            }
        }
        return instance;
    }

    public User getUser(String userName) {
        return registeredUsers.get(userName);
    }

    /**
     * registers a new user with the given parameters
     *
     * @param userType
     * @param userName
     * @param email
     * @param password
     * @param dateOfBirth
     * @param gender
     * @param car
     * @return the registered user or null if the username is already taken
     * @throws userNameTakenException
     */
    public User registerUser(UserType userType, String userName, String email, String password, LocalDate dateOfBirth,
            String gender, Car car) throws userNameTakenException {

        User potentialUser = getUser(userName);

        if (potentialUser == null) {
            registeredUsers.put(userName, new User(userType, userName, email, password, dateOfBirth, gender, car));
            // change to next scene with user already logged in
            System.out.println("User registered successfully!");
            return registeredUsers.get(userName);
        }

        throw new userNameTakenException("Username already taken. Please choose a different username.");
    }

    /**
     * registers a new premium user with the given parameters
     *
     * @param userType
     * @param userName
     * @param email
     * @param password
     * @param dateOfBirth
     * @param gender
     * @param car
     * @param card
     * @return the registered user or null if the username is already taken
     * @throws userNameTakenException if the username is already taken
     */
    public User registerPremiumUser(UserType userType, String userName, String email, String password,
            LocalDate dateOfBirth, String gender, Car car, PaymentCard card) throws userNameTakenException {
        User potentialUser = getUser(userName);

        if (potentialUser == null) {
            registeredUsers.put(userName,
                    new PremiumUser(userType, userName, email, password, dateOfBirth, gender, car, card));
            // change to next scene with user already logged in
            System.out.println("User registered successfully!");
            return registeredUsers.get(userName);
        }

        throw new userNameTakenException("Username already taken. Please choose a different username.");
    }

    /**
     * deletes the given user from the registered users also deletes admin
     *
     * @param in
     * @return the deleted user
     * @throws userDoesNotExistException
     */
    public User deleteUser(User in) throws userDoesNotExistException {
        User potentialUser = getUser(in.getUserName());
        if (potentialUser != null) {
            registeredUsers.remove(in.getUserName());
            System.out.println("User deleted successfully!");
            return potentialUser;
        }

        throw new userDoesNotExistException("User does not exist.");
    }

    /**
     * deletes the given user from the registered users only admin can delete others
     * an admin cannot delete themselves nor other admins
     *
     * @param in
     * @param admin
     * @return the deleted user
     * @throws noPermissionException
     * @throws userDoesNotExistException
     */
    public User deleteUser(User in, User admin) throws noPermissionException, userDoesNotExistException {
        if (in == null || admin == null)
            throw new NullPointerException("User or admin");

        if (admin.getUserType() != UserType.ADMIN) {
            throw new noPermissionException();
        }

        User potentialUser = getUser(in.getUserName());
        if (potentialUser != null) {
            if (potentialUser.getUserType() == UserType.ADMIN || potentialUser == admin) {
                throw new noPermissionException("Admin cannot delete other admin or themselves.");
            }
            registeredUsers.remove(in.getUserName());
            System.out.println("User deleted successfully!");
            return potentialUser;
        }

        throw new userDoesNotExistException("User does not exist.");
    }

    /**
     * changes the username of the given user to the new username if the new
     * username is not taken
     *
     * @param in
     * @param newUserName
     * @return the user with the new username
     * @throws userDoesNotExistException if the user does not exist
     * @throws userNameTakenException    if the new username is taken
     */
    public User changeUserName(User in, String newUserName) throws userDoesNotExistException, userNameTakenException {
        // check if the new username is taken
        User potentialUser = getUser(in.getUserName());
        registeredUsers.remove(in.getUserName());
        if (getUser(newUserName) != null) {
            registeredUsers.put(in.getUserName(), potentialUser); // put the user back
            throw new userNameTakenException("Username already taken. Please choose a different username.");
        }

        // change the username
        if (potentialUser != null) {
            potentialUser.setUserName(newUserName);
            registeredUsers.put(newUserName, potentialUser);
            System.out.println("Username changed successfully!");
            return potentialUser;
        }
        throw new userDoesNotExistException("User does not exist.");
    }

    /**
     * changes the given user to premium user if the user is not already premium and
     * the given card is valid
     *
     * @param in
     * @param cardNumber
     * @param expirationDate
     * @param securityCode
     * @return
     * @throws userDoesNotExistException
     */
    public User changeToPremium(User in, String cardNumber, LocalDate expirationDate, String securityCode)
            throws userDoesNotExistException, wrongCardDetailsException {
        User potentialUser = getUser(in.getUserName());
        if (potentialUser != null) {
            deleteUser(potentialUser);
            try {
                registerPremiumUser(UserType.PREMIUM_USER, potentialUser.getUserName(), potentialUser.getEmail(),
                        potentialUser.getPassword(), potentialUser.getDateOfBirth(), potentialUser.getGender(),
                        potentialUser.getCar(), new PaymentCard(cardNumber, expirationDate, securityCode));
            } catch (userNameTakenException e) {
                e.printStackTrace(); // this should never happen
            } catch (wrongCardDetailsException e) {
                // change back to basic user
                try {
                    registerUser(UserType.BASIC_USER, potentialUser.getUserName(), potentialUser.getEmail(),
                            potentialUser.getPassword(), potentialUser.getDateOfBirth(), potentialUser.getGender(),
                            potentialUser.getCar());
                } catch (userNameTakenException e1) {
                    e1.printStackTrace(); // this should never happen
                }
                throw e;
            }

            System.out.println("User changed to premium successfully!");
            return potentialUser;
        }

        throw new userDoesNotExistException("User does not exist.");
    }

    public User changeToPremium(User in, User admin) throws noPermissionException, userDoesNotExistException {
        if (admin.getUserType() != UserType.ADMIN) {
            throw new noPermissionException();
        }
        User potentialUser = getUser(in.getUserName());

        if (potentialUser != null) {
            deleteUser(potentialUser);
            try {
                registerPremiumUser(UserType.PREMIUM_USER, potentialUser.getUserName(), potentialUser.getEmail(),
                        potentialUser.getPassword(), potentialUser.getDateOfBirth(), potentialUser.getGender(),
                        potentialUser.getCar(), new PaymentCard());
            } catch (userNameTakenException e) {
                e.printStackTrace(); // this should never happen
            }

            System.out.println("User changed to premium successfully!");
            return potentialUser;
        }

        throw new userDoesNotExistException("User does not exist.");
    }

    public User changeToAdmin(User in, User admin) throws noPermissionException, userDoesNotExistException {
        if (admin.getUserType() != UserType.ADMIN) {
            throw new noPermissionException();
        }
        User potentialUser = getUser(in.getUserName());

        if (potentialUser != null) {
            potentialUser.setUserType(UserType.ADMIN);
            return potentialUser;
        }

        throw new userDoesNotExistException("User does not exist.");
    }

    // public User deleteUser(User in, User admin) throws userDoesNotExistException,
    // noPermissionException {
    // User potentialUser = getUser(in.getUserName());
    // if (potentialUser.getUserType() == UserType.ADMIN || potentialUser == admin)
    // {
    // throw new noPermissionException("Admin cannot delete other admin or
    // themselves.");
    // }
    // deleteUser(in);
    // }

    public User addCarToUser(User in, int year, String licenseNumber, String model, FuelType fuel,
            double fuelConsumption, int fuelTankCapacity, int currentFuelLevel, Location location)
            throws userDoesNotExistException {
        User potentialUser = getUser(in.getUserName());

        if (potentialUser != null) {
            potentialUser.setCar(new Car(year, licenseNumber, model, fuel, fuelConsumption, fuelTankCapacity,
                    currentFuelLevel, location));
            System.out.println("Car added successfully!");
            return potentialUser;
        } else {
            throw new userDoesNotExistException("User does not exist.");
        }
    }

    public void saveToFile(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(registeredUsers);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Map<String, User> loadFromFile(String filename) {
        Map<String, User> registeredUsers = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            registeredUsers = (Map<String, User>) in.readObject();
        } catch (IOException i) {
            System.out.println("File not found: " + filename + ". Creating a new file.");
            registeredUsers = new HashMap<>();
        } catch (ClassNotFoundException c) {
            System.out.println("User class not found");
            c.printStackTrace();
        }
        return registeredUsers;
    }

    // TODO: remove this method
    public Map<String, User> getRegisteredUsers() {
        return registeredUsers;
    }
}
