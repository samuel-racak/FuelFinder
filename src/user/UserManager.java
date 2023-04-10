package user;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static UserManager instance;
    private static List<User> registeredUsers;

    private UserManager() {
        registeredUsers = new ArrayList<>();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // the search could be done in a better way by inserting users into some kind of
    // a data structure (AVL tree) will maybe add this later
    public User userExist(String username) {
        for (User user : registeredUsers) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User registerUser(User in) {
        User potentialUser = userExist(in.getName());

        if (potentialUser != null) {
            registeredUsers.add(potentialUser);
            // change to next scene with user already logged in
            System.out.println("User registered successfully!");
            return potentialUser;
        } else {
            // handle the situation
            System.out.println("Username already taken. Please choose a different username.");
        }
        return null;
    }

    // TODO add more methods for logging user in, deletion of user etc.

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
