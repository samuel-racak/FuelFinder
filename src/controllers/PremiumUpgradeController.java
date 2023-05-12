package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import user.PaymentCard;

public class PremiumUpgradeController extends BasicController {
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField expirationDateField;
    @FXML
    private TextField securityCodeField;

    @FXML
    private void handleSubmitButtonAction() {
        System.out.println("Submit button clicked!");

        // Get the entered values from the form fields
        String cardNumber = cardNumberField.getText();
        String expirationDate = expirationDateField.getText();
        String securityCode = securityCodeField.getText();

        // Create a new PaymentCard object with the entered values
        PaymentCard card = new PaymentCard(cardNumber, expirationDate, securityCode);

        // TODO: Do something with the created PaymentCard object
    }

    @Override
    public void setTitle() {
        stage.setTitle("Premium Upgrade");
    }
}
