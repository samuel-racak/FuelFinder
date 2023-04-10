package user;

import java.time.LocalDate;
import car.Car;

public class Admin extends User {
    private UserManager manager;

    public Admin(String name, String email, String password, LocalDate dataOfBirth, String gender,
            Car car) {
        super(UserType.ADMIN, name, email, password, dataOfBirth, gender, car);
        manager = UserManager.getInstance();
    }

    // TODO add methods for managing users such as add, remove, changeToPremium
}
