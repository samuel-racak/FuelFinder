package user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import car.Car;
import exceptions.noPermissionException;

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
     * @param dataOfBirth
     * @param gender
     * @param car
     * @return the registered user or null if the username is already taken
     */
    public User registerUser(UserType userType, String userName, String email, String password, LocalDate dataOfBirth,
            String gender, Car car) {

        User potentialUser = getUser(userName);

        if (potentialUser == null) {
            registeredUsers.put(userName, new User(userType, userName, email, password, dataOfBirth, gender, car));
            // change to next scene with user already logged in
            System.out.println("User registered successfully!");
            return registeredUsers.get(userName);
        }

        // TODO: handle the situation
        System.out.println("Username already taken. Please choose a different username.");
        return null;
    }

    public User registerPremiumUser(UserType userType, String userName, String email, String password,
            LocalDate dataOfBirth, String gender, Car car, PaymentCard card) {
        User potentialUser = getUser(userName);

        if (potentialUser == null) {
            registeredUsers.put(userName,
                    new PremiumUser(userType, userName, email, password, dataOfBirth, gender, car, card));
            // change to next scene with user already logged in
            System.out.println("User registered successfully!");
            return registeredUsers.get(userName);
        }
        // TODO: handle the situation
        System.out.println("Username already taken. Please choose a different username.");
        return null;
    }

    public User deleteUser(User in, User admin) throws noPermissionException {
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
        return null; // user not found
    }

    public User changeToPremium(User in, User admin) throws noPermissionException {
        if (admin.getUserType() != UserType.ADMIN) {
            throw new noPermissionException();
        }
        User potentialUser = getUser(in.getUserName());

        if (potentialUser != null) {
            potentialUser.setUserType(UserType.PREMIUM_USER);
            System.out.println("User changed to premium successfully!");
            return potentialUser;
        } else {
            // TODO: handle the situation
            System.out.println("User does not exist.");
        }
        return null;
    }

    public User changeToAdmin(User in, User admin) throws noPermissionException {
        if (admin.getUserType() != UserType.ADMIN) {
            throw new noPermissionException();
        }
        User potentialUser = getUser(in.getUserName());

        if (potentialUser != null) {
            potentialUser.setUserType(UserType.ADMIN);
            return potentialUser;
        } else {
            // TODO: handle the situation
            System.out.println("User does not exist.");
        }
        return null;
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

    // public void saveToFile(String filename) {
    // URL resourceUrl = getClass().getResource(filename);
    // if (resourceUrl != null) {
    // String filePath = new File(resourceUrl.getFile()).getAbsolutePath();
    // try (FileOutputStream fileOut = new FileOutputStream(filePath);
    // ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
    // out.writeObject(registeredUsers);
    // } catch (IOException i) {
    // i.printStackTrace();
    // }
    // } else {
    // System.out.println("File not found: " + filename);
    // }
    // }

    // public static Map<String, User> loadFromFile(String filename) {
    // Map<String, User> registeredUsers = null;
    // URL resourceUrl = UserManager.class.getResource(filename);
    // if (resourceUrl != null) {
    // String filePath = new File(resourceUrl.getFile()).getAbsolutePath();
    // try (FileInputStream fileIn = new FileInputStream(filePath);
    // ObjectInputStream in = new ObjectInputStream(fileIn)) {
    // registeredUsers = (Map<String, User>) in.readObject();
    // } catch (IOException i) {
    // i.printStackTrace();
    // } catch (ClassNotFoundException c) {
    // System.out.println("User class not found");
    // c.printStackTrace();
    // }
    // } else {
    // System.out.println("File not found: " + filename);
    // }
    // return registeredUsers;
    // }

    // public void saveToFile(String filename) {
    // try (FileOutputStream fileOut = new FileOutputStream(filename);
    // ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
    // out.writeObject(registeredUsers);
    // } catch (IOException i) {
    // i.printStackTrace();
    // }
    // }

    // public static Map<String, User> loadFromFile(String filename) {
    // Map<String, User> registeredUsers = null;
    // try (FileInputStream fileIn = new FileInputStream(filename);
    // ObjectInputStream in = new ObjectInputStream(fileIn)) {
    // registeredUsers = (Map<String, User>) in.readObject();

    // } catch (IOException i) {
    // i.printStackTrace();
    // } catch (ClassNotFoundException c) {
    // System.out.println("User class not found");
    // c.printStackTrace();
    // }
    // return registeredUsers;
    // }

}
