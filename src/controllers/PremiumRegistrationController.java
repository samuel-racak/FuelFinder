package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PremiumRegistrationController extends BasicController {
    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField userEmailTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private DatePicker dateOfBirthPicker;

    @FXML
    private TextField userGenderTextField;

    @FXML
    private TextField cardNumberField;

    @FXML
    private DatePicker cardExpirationDate;

    @FXML
    private TextField cardCCVField;

    @FXML
    private Button checkCardButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button goBackButton;

    @FXML
    private ImageView imageView;

    private static final int NUM_IMAGES = 3;
    private int currentImageIndex = 0;
    private static final String IMAGE_LOC = "/resources/Cyber";
    private static final int TIME = 2;

    @FXML
    public void initialize() {
        imageView.setImage(new Image(IMAGE_LOC + "1.jpg"));
        // imageView.setImage(new Image(getClass().getResource(IMAGE_LOC +
        // "1.jpg").toExternalForm()));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(TIME), event -> {
            currentImageIndex = (currentImageIndex + 1) % NUM_IMAGES;
            imageView.setImage(new Image(IMAGE_LOC + (currentImageIndex + 1) + ".jpg"));
            // imageView.setImage(
            // new Image(getClass().getResource(IMAGE_LOC + (currentImageIndex + 1) +
            // ".jpg").toExternalForm()));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void checkCardDetails(ActionEvent event) {
        if (cardNumberField.getText().isEmpty() || cardExpirationDate.getValue() == null
                || cardCCVField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid card details");
            alert.setContentText("Please fill all the fields and try again");
            alert.showAndWait();
            return;
        }

        if (sessionManager.checkCardDetails(cardNumberField.getText(), cardExpirationDate.getValue(),
                cardCCVField.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Card details are valid");
            alert.setContentText("You can now register as a premium user");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid card details");
            alert.setContentText("Please check your card details and try again");
            alert.showAndWait();
        }

        System.out.println("checking card details");
        System.out.println("card number:" + cardNumberField.getText());
        System.out.println("card expiration date:" + cardExpirationDate.getValue());
        System.out.println("card CCV:" + cardCCVField.getText());
    }

    @FXML
    private void createUser(ActionEvent event) {
        if (userNameTextField.getText().isEmpty() || userEmailTextField.getText().isEmpty()
                || passwordTextField.getText().isEmpty() || dateOfBirthPicker.getValue() == null
                || userGenderTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid user details");
            alert.setContentText("Please fill all the fields and try again");
            alert.showAndWait();
        } else {
            sessionManager.registerPremiumUser(userNameTextField.getText(), userEmailTextField.getText(),
                    passwordTextField.getText(), dateOfBirthPicker.getValue(), userGenderTextField.getText(),
                    cardNumberField.getText(), cardExpirationDate.getValue(), cardCCVField.getText());
        }
        // System.out.println("Registration controller");
        // System.out.println("user wants to register");
        // System.out.println("user name:" + userNameTextField.getText());
        // System.out.println("user email:" + userEmailTextField.getText());
        // System.out.println("user password:" + passwordTextField.getText());
        // System.out.println("user date of birth:" + dateOfBirthPicker.getValue());
        windowManager.switchToScene("primaryStage", "loginScene");
    }

    @FXML
    private void goBack(ActionEvent event) {
        System.out.println("going back to registration scene");
        windowManager.switchToScene("primaryStage", "registerScene");
    }

    @Override
    public void setTitle() {
        stage.setTitle("Premium registration");
    }
}
