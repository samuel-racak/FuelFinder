package user;

import java.time.LocalDate;

import car.Car;

public class User {

    private UserType role = UserType.BASIC_USER; // this indicates that user is not admin
    private String name;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private String gender;
    private Car car;

    /**
     * this is a basic user account created by UserManager
     *
     * @param name
     * @param email
     * @param password
     * @param dataOfBirth
     * @param gender
     * @param car
     */
    public User(UserType role, String name, String email, String password, LocalDate dataOfBirth, String gender, Car car) {
        this.role = role;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    publc
}
