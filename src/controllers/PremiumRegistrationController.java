package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TextField cardNumber;

    @FXML
    private DatePicker cardExpirationDate;

    @FXML
    private TextField cardCCV;

    @FXML
    private Button checkCardButton;

    @FXML
    private Button loginButton;

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
        System.out.println("checking card details");
        System.out.println("card number:" + cardNumber.getText());
        System.out.println("card expiration date:" + cardExpirationDate.getValue());
        System.out.println("card CCV:" + cardCCV.getText());
    }

    @FXML
    private void createUser(ActionEvent event) {
        System.out.println("Registration controller");
        System.out.println("user wants to register");
        System.out.println("user name:" + userNameTextField.getText());
        System.out.println("user email:" + userEmailTextField.getText());
        System.out.println("user password:" + passwordTextField.getText());
        System.out.println("user date of birth:" + dateOfBirthPicker.getValue());
        windowManager.switchToScene("primaryStage", "loginScene");

        // TODO add user and go to other scene
    }

    @Override
    public void setTitle() {
        stage.setTitle("Premium registration");
    }
}
