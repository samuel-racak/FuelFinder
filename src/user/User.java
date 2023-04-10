package user;

import java.time.LocalDate;

public class User {

    private String name;
    private String email;
    private String password;
    private LocalDate dateOfBirth;

    /**
     * @param name
     * @param email
     * @param password
     * @param dataOfBirth
     */
    public User(String name, String email, String password, LocalDate dataOfBirth) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
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
}
