package user;

import java.io.Serializable;
import java.time.LocalDate;

import car.Car;

public class User implements Comparable<User>, Serializable {

    private UserType userType; // this indicates that user is not admin
    private String userName;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private String gender;
    private Car car;

    /**
     * this is a basic user account created by UserManager
     *
     * @param userType
     * @param name
     * @param email
     * @param password
     * @param dataOfBirth
     * @param gender
     * @param car
     */
    public User(UserType userType, String name, String email, String password, LocalDate dataOfBirth, String gender,
            Car car) {
        this.userType = userType;
        this.userName = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.car = car;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public int compareTo(User other) {
        return this.userName.compareTo(other.getUserName());
    }

}
