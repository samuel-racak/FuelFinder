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

/**
 * This class is the controller for the premium registration scene of the
 * application.
 */
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
    private Button RegisterButton;

    @FXML
    private Button goBackButton;

    @FXML
    private ImageView imageView;

    private static final int NUM_IMAGES = 3;
    private int currentImageIndex = 0;
    private static final String IMAGE_LOC = "/resources/Cyber";
    private static final int TIME = 2;

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        imageView.setImage(new Image(IMAGE_LOC + "1.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(TIME), event -> {
            currentImageIndex = (currentImageIndex + 1) % NUM_IMAGES;
            imageView.setImage(new Image(IMAGE_LOC + (currentImageIndex + 1) + ".jpg"));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Checks the entered card details.
     *
     * @param event The event that triggered this method.
     */
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

    /**
     * Creates a new user.
     *
     * @param event The event that triggered this method.
     */
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
            if (sessionManager.registerPremiumUser(userNameTextField.getText(), userEmailTextField.getText(),
                    passwordTextField.getText(), dateOfBirthPicker.getValue(), userGenderTextField.getText(),
                    cardNumberField.getText(), cardExpirationDate.getValue(), cardCCVField.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("User created");
                alert.setContentText("You can now login as a premium user");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("User name already exists");
                alert.setContentText("Please check your user details and try again");
                alert.showAndWait();
                return;
            }
            windowManager.switchToScene("primaryStage", "loginScene");
        }
    }

    /**
     * Goes back to the registration scene.
     *
     * @param event The event that triggered this method.
     */
    @FXML
    private void goBack(ActionEvent event) {
        System.out.println("going back to registration scene");
        // userEmailTextField.clear();
        // userNameTextField.clear();
        // passwordTextField.clear();
        // dateOfBirthPicker.setValue(null);
        // userGenderTextField.clear();
        // cardNumberField.clear();
        // cardExpirationDate.setValue(null);
        // cardCCVField.clear();

        windowManager.switchToScene("primaryStage", "registerScene");
    }

    /**
     * Sets the title of the stage.
     */
    @Override
    public void setTitle() {
        stage.setTitle("Premium registration");
    }
}
