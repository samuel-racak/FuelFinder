package user;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import exceptions.noPermissionException;

public class UserManager {
    private static UserManager instance;
    private static List<User> registeredUsers; // could use TreeMap to save users

    private UserManager() {
        registeredUsers = new ArrayList<>();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User getUser(String username) {
        for (User user : registeredUsers) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User registerUser(User in) {
        User potentialUser = getUser(in.getName());

        if (potentialUser == null) {
            registeredUsers.add(potentialUser);
            // change to next scene with user already logged in
            System.out.println("User registered successfully!");
            return potentialUser;
        } else {
            // TODO: handle the situation
            System.out.println("Username already taken. Please choose a different username.");
        }
        return null;
    }

    public User deleteUser(User in, User admin) throws noPermissionException {
        if (admin.getUserType() != UserType.ADMIN) {
            throw new noPermissionException();
        }

        User potentialUser = getUser(in.getName());
        if (potentialUser != null) {
            if (potentialUser.getUserType() == UserType.ADMIN) {
                throw new noPermissionException("Admin cannot other admin.");
            }
            registeredUsers.remove(potentialUser);
            System.out.println("User deleted successfully!");
            return potentialUser;
        }
        return null; // user not found
    }

    public User changeToPremium(User in, User admin) throws noPermissionException {
        if (admin.getUserType() != UserType.ADMIN) {
            throw new noPermissionException();
        }
        User potentialUser = getUser(in.getName());

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
        User potentialUser = getUser(in.getName());

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

    public static User loadFromFile(String filename) {
        User user = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            registeredUsers = (List<User>) in.readObject();

        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("User class not found");
            c.printStackTrace();
        }
        return user;
    }

}
