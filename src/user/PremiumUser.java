package user;

import java.time.LocalDate;

import vehicle.Car;

public class PremiumUser extends User {
    private PaymentCard card;

    public PremiumUser(UserType userType, String userName, String email, String password, LocalDate dataOfBirth,
            String gender, Car car, PaymentCard card) {
        super(userType, userName, email, password, dataOfBirth, gender, car);
        this.card = card;
    }

    public PaymentCard getCard() {
        return card;
    }
}
