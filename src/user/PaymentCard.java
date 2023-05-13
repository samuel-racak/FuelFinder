package user;

import exceptions.wrongCardDetailsException;

public class PaymentCard {
    private String cardNumber;
    private String expirationDate;
    private String securityCode;

    public PaymentCard(String cardNumber, String expirationDate, String securityCode) throws wrongCardDetailsException {
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
        int sum = 0;
        int parity = cardNumber.length() % 2;
        for (int i = 0; i < cardNumber.length(); i++) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }
}