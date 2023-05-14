package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PremiumUpgradeController extends BasicController {
    @FXML
    private TextField cardNumberField;

    @FXML
    private DatePicker cardExpirationDate;

    @FXML
    private TextField securityCodeField;

    @FXML
    private void handleSubmitButtonAction() {
        if (cardNumberField.getText().isEmpty() || cardExpirationDate.getValue() == null
                || securityCodeField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid card details");
            alert.setContentText("Please fill all the fields and try again");
            alert.showAndWait();
            return;
        }

        if (!sessionManager.changeToPremium(sessionManager.getCurrentUser(), cardNumberField.getText(),
                cardExpirationDate.getValue(), securityCodeField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid card details");
            alert.setContentText("Please check your card details and try again");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("You are now a premium user");
            alert.setContentText("You can now enjoy all the premium features");
            alert.showAndWait();
        }

        Stage stage = (Stage) cardNumberField.getScene().getWindow(); // get the current stage
        stage.close(); // close the current window
    }

    @Override
    public void setTitle() {
        stage.setTitle("Premium Upgrade");
    }

    @Override
    public void fillGUI() {
        cardNumberField.clear();
        cardExpirationDate.setValue(null);
        securityCodeField.clear();
    }
}
