package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
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
 * This class is the controller for the registration scene of the application.
 */
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
    private Button registerButton;

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
     * Creates a new user.
     *
     * @param event The event that triggered this method.
     */
    @FXML
    private void createUser(ActionEvent event) {
        if (userNameTextField.getText().isEmpty() || userEmailTextField.getText().isEmpty()
                || passwordTextField.getText().isEmpty() || dateOfBirthPicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Registration Error");
            alert.setContentText("Please enter all fields");
            alert.showAndWait();
        } else {
            Task<Boolean> registerTask = new Task<Boolean>() {
                @Override
                protected Boolean call() {
                    return sessionManager.registerUser(userNameTextField.getText(), userEmailTextField.getText(),
                            passwordTextField.getText(), dateOfBirthPicker.getValue(),
                            userGenderTextField.getText());
                }
            };

            registerTask.setOnSucceeded(registerEvent -> {
                if (registerTask.getValue()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Registration Success");
                    alert.setContentText("User registered successfully");
                    alert.showAndWait();
                    windowManager.switchToScene("primaryStage", "loginScene");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Registration Error");
                    alert.setContentText("User already exists");
                    alert.showAndWait();
                }
            });

            Thread thread = new Thread(registerTask);
            thread.setDaemon(true);
            thread.start();
        }

        System.out.println("user name:" + userNameTextField.getText());
        System.out.println("user email:" + userEmailTextField.getText());
        System.out.println("user password:" + passwordTextField.getText());
        System.out.println("user date of birth:" + dateOfBirthPicker.getValue());
        System.out.println("user gender:" + userGenderTextField.getText());
    }

    /**
     * Goes to the premium scene.
     *
     * @param event The event that triggered this method.
     */
    @FXML
    private void goPremium(ActionEvent event) {
        System.out.println("user wants to go premium");
        windowManager.switchToScene("primaryStage", "premiumScene");
    }

    /**
     * Goes back to the login scene.
     *
     * @param event The event that triggered this method.
     */
    @FXML
    private void goBack(ActionEvent event) {
        System.out.println("user wants to go back");
        userEmailTextField.clear();
        userNameTextField.clear();
        passwordTextField.clear();
        dateOfBirthPicker.setValue(null);
        userGenderTextField.clear();
        windowManager.switchToScene("primaryStage", "loginScene");
    }

    /**
     * Sets the title of the stage.
     */
    @Override
    public void setTitle() {
        stage.setTitle("Registration");
    }
}
