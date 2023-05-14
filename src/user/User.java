package user;

import java.io.Serializable;
import java.time.LocalDate;

import vehicle.Car;

/**
 * The User class represents a user of the application.
 * It implements the Comparable and Serializable interfaces.
 */
public class User implements Comparable<User>, Serializable {

    private UserType userType; // this indicates that user is not admin
    private String userName;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private String gender;
    private Car car;

    /**
     * Constructor for the User class.
     * Initializes the user type, name, email, password, date of birth, gender and
     * car to the given values.
     *
     * @param userType    the user type
     * @param name        the name of the user
     * @param email       the email of the user
     * @param password    the password of the user
     * @param dateOfBirth the date of birth of the user
     * @param gender      the gender of the user
     * @param car         the car of the user
     */
    public User(UserType userType, String name, String email, String password, LocalDate dateOfBirth, String gender,
            Car car) {
        this.userType = userType;
        this.userName = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.car = car;
        System.out.println(dateOfBirth);
    }

    /**
     * Returns the user type of the user.
     *
     * @return the user type of the user
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sets the user type of the user.
     *
     * @param userType the new user type to set
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the new name to set
     */
    public void setUserName(String name) {
        this.userName = name;
    }

    /**
     * Returns the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the new email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the date of birth of the user.
     *
     * @return the date of birth of the user
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the user.
     *
     * @param dateOfBirth the new date of birth to set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the gender of the user.
     *
     * @return the gender of the user
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender the new gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns the car of the user.
     *
     * @return the car of the user
     */
    public Car getCar() {
        return car;
    }

    /**
     * Sets the car of the user.
     *
     * @param car the new car to set
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Compares this user to another user based on their names.
     *
     * @param other the other user to compare to
     * @return a negative integer, zero, or a positive integer as this user's name
     *         is less than, equal to, or greater than the other user's name
     */
    @Override
    public int compareTo(User other) {
        return this.userName.compareTo(other.getUserName());
    }

    /**
     * Returns a string representation of this user.
     *
     * @return a string representation of this user
     */
    @Override
    public String toString() {
        return "User Type: " + userType + "\n" +
                "Username: " + userName + "\n" +
                "Email: " + email + "\n" +
                "Password: " + password + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Gender: " + gender + "\n" +
                "Car: " + (car != null ? car.toString() : "None");
    }
}
