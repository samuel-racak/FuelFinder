package user;

import java.io.Serializable;
import java.time.LocalDate;

import exceptions.wrongCardDetailsException;

public class PaymentCard implements Serializable {
    private String cardNumber;
    private LocalDate expirationDate;
    private String securityCode;

    public PaymentCard() {
        // TODO: handle this situation
        this.cardNumber = "";
        this.expirationDate = LocalDate.now();
        this.securityCode = "";
    }

    public PaymentCard(String cardNumber, LocalDate expirationDate, String securityCode)
            throws wrongCardDetailsException {
        if (isValid(cardNumber)) {
            this.cardNumber = cardNumber;
            this.expirationDate = expirationDate;
            this.securityCode = securityCode;
        } else {
            // TODO handle this situation
            System.out.print("wrong card number");
            throw new wrongCardDetailsException();
        }
    }

    /**
     * Takes cardNumber and checks if card is valid
     * Luhns algorithm made see the
     * <a href="https://en.wikipedia.org/wiki/Luhn_algorithm"</a>
     *
     * @param cardNumber
     * @return true if valid, else false
     */
    public static boolean isValid(String cardNumber) {
        cardNumber = cardNumber.replaceAll(" ", ""); // remove all spaces
        int sum = 0;
        int parity = cardNumber.length() % 2;
        for (int i = 0; i < cardNumber.length(); i++) {
            char c = cardNumber.charAt(i);
            int digit = Character.getNumericValue(c);
            if (i % 2 != parity) {
                sum += digit;
            } else if (digit > 4) {
                sum += 2 * digit - 9;
            } else {
                sum += 2 * digit;
            }
        }
        return sum % 10 == 0;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }
}