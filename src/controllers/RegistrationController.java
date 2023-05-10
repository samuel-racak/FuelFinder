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

public class RegistrationController extends BasicController {

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
    private Button goPremiumButton;

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

    @Override
    public void setTitle() {
        stage.setTitle("Registration");
    }

    @FXML
    private void createUser(ActionEvent event) {
        System.out.println("Registration controller");
        System.out.println("user wants to register");
        System.out.println("user name:" + userNameTextField.getText());
        System.out.println("user email:" + userEmailTextField.getText());
        System.out.println("user password:" + passwordTextField.getText());
        System.out.println("user date of birth:" + dateOfBirthPicker.getValue());
        sceneManager.switchToScene("login");

        // TODO add user and go to other scene
    }

    @FXML
    private void goPremium(ActionEvent event) {
        System.out.println("user wants to go premium");
        sceneManager.switchToScene("premium");
        // TODO save user details and go to other scene
    }
}
