package user;

import java.io.Serializable;
import java.time.LocalDate;

import exceptions.wrongCardDetailsException;

/**
 * The PaymentCard class represents a payment card.
 * It implements the Serializable interface to allow it to be serialized.
 */
public class PaymentCard implements Serializable {
    private String cardNumber;
    private LocalDate expirationDate;
    private String securityCode;

    /**
     * Default constructor for the PaymentCard class.
     * Initializes the card number, expiration date and security code to default
     * values.
     */
    public PaymentCard() {
        this.cardNumber = "";
        this.expirationDate = LocalDate.now();
        this.securityCode = "";
    }

    /**
     * Constructor for the PaymentCard class.
     * Initializes the card number, expiration date and security code to the given
     * values.
     *
     * @param cardNumber     the card number
     * @param expirationDate the expiration date
     * @param securityCode   the security code
     * @throws wrongCardDetailsException if the card number is not valid
     */
    public PaymentCard(String cardNumber, LocalDate expirationDate, String securityCode)
            throws wrongCardDetailsException {
        if (isValid(cardNumber)) {
            this.cardNumber = cardNumber;
            this.expirationDate = expirationDate;
            this.securityCode = securityCode;
        } else {
            throw new wrongCardDetailsException();
        }
    }

    /**
     * Takes cardNumber and checks if card is valid
     * Luhns algorithm made see the
     * {@link https://en.wikipedia.org/wiki/Luhn_algorithm}
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

    /**
     * Returns the card number of the payment card.
     *
     * @return the card number of the payment card
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Returns the expiration date of the payment card.
     *
     * @return the expiration date of the payment card
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Returns the security code of the payment card.
     *
     * @return the security code of the payment card
     */
    public String getSecurityCode() {
        return securityCode;
    }
}
