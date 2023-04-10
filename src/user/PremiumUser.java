package user;

import java.time.LocalDate;

import car.Car;

public class PremiumUser extends User {
    private PaymentCard card;

    public PremiumUser(UserType userType, String name, String email, String password, LocalDate dataOfBirth,
            String gender, Car car, PaymentCard card) {
        super(userType, name, email, password, dataOfBirth, gender, car);
        this.card = card;
    }

    // TODO add some methods for premium user
}
