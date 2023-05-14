package controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The SettingsController class extends the BasicController class and provides
 * functionality for managing user settings.
 */
public class SettingsController extends BasicController {

    /**
     * The changeUserNameButton is a Button that allows the user to change their
     * username.
     */
    @FXML
    private Button changeUserNameButton;

    /**
     * The deleteAccountButton is a Button that allows the user to delete their
     * account.
     */
    @FXML
    private Button deleteAccountButton;

    /**
     * The generateNewCarButton is a Button that allows the user to generate a new
     * car.
     */
    @FXML
    private Button generateNewCarButton;

    /**
     * The generateRandomCarButton is a Button that allows the user to generate a
     * random car.
     */
    @FXML
    private Button generateRandomCarButton;

    /**
     * The goBackButton is a Button that allows the user to go back to the previous
     * screen.
     */
    @FXML
    private Button goBackButton;

    /**
     * The goPremiumButton is a MenuItem that allows the user to upgrade to a
     * premium account.
     */
    @FXML
    private MenuItem goPremiumButton;

    /**
     * The imageView is an ImageView that displays an image.
     */
    @FXML
    private ImageView imageView;

    /**
     * The logoutButton is a Button that allows the user to log out of their
     * account.
     */
    @FXML
    private Button logoutButton;

    /**
     * The menuButton is a MenuButton that displays a menu of options for the user.
     */
    @FXML
    private MenuButton menuButton;

    /**
     * The userNameLabel is a Label that displays the username of the current user.
     */
    @FXML
    private Label userNameLabel;

    /**
     * The newUserName is a TextField that allows the user to enter a new username.
     */
    @FXML
    private TextField newUserName;

    /**
     * The carInformationListView is a ListView that displays information about the
     * current user's car.
     */
    @FXML
    private ListView<String> carInformationListView;

    /**
     * Initializes the SettingsController by setting up the imageView with a Circle
     * clip and binding its properties.
     */
    @FXML
    void initialize() {
        // Create a Circle with the desired radius
        // choose either imageView.getFitWidth() or imageView.getFitHeight() depending
        // on which is smaller

        int radius = (int) Math.min(imageView.getFitWidth(), imageView.getFitHeight()) / 2;
        Circle clip = new Circle(radius);

        // Set the center of the Circle to the center of the ImageView
        clip.centerXProperty().bind(imageView.fitWidthProperty().divide(2));
        clip.centerYProperty().bind(imageView.fitHeightProperty().divide(2));

        // Set the clip property of the ImageView to the Circle
        imageView.setClip(clip);
    }

    /**
     * Handles an ActionEvent on the carCreation button by opening a pop-up window
     * for car creation.
     *
     * @param event The ActionEvent on the carCreation button.
     */
    @FXML
    void carCreation(ActionEvent event) {
        System.out.println("Car Creation");
        Stage popUpStage = new Stage();
        popUpStage.initOwner(stage);
        popUpStage.initModality(Modality.WINDOW_MODAL);
        windowManager.addStage("popUpStage", popUpStage);
        windowManager.switchToScene("popUpStage", "carScene");
        windowManager.getScene("carScene").addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                System.out.println("ESC");
                windowManager.removeStage("popUpStage");
            }
        });
    }

    /**
     * Handles an ActionEvent on the changeUsername button by changing the username
     * of the current user if it does not already exist.
     *
     * @param event The ActionEvent on the changeUsername button.
     */
    @FXML
    void changeUsername(ActionEvent event) {
        Task<Boolean> changeUsernameTask = new Task<>() {
            @Override
            protected Boolean call() {
                return sessionManager.changeUserName(newUserName.getText());
            }
        };

        changeUsernameTask.setOnSucceeded(e -> {
            if (changeUsernameTask.getValue()) {
                userNameLabel.setText(newUserName.getText());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username already exists");
                alert.setContentText("Please choose another username");
                alert.showAndWait();
            }
        });
        Thread thread = new Thread(changeUsernameTask);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Handles an ActionEvent on the deleteAccount button by deleting the current
     * user's account if it exists and is not an admin account.
     *
     * @param event The ActionEvent on the deleteAccount button.
     */
    @FXML
    void deleteAccount(ActionEvent event) {
        if (!sessionManager.deleteUser()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User not deleted does not exist");
            alert.setContentText("You cannot delete a user that does not exist or is an admin");
            alert.showAndWait();
        } else {
            windowManager.switchToScene("primaryStage", "loginScene");
        }
    }

    /**
     * Handles an ActionEvent on the generateRandomCar button by generating a random
     * car for the current user.
     *
     * @param event The ActionEvent on the generateRandomCar button.
     */
    @FXML
    void generateRandomCar(ActionEvent event) {
        sessionManager.generateRandomCar(sessionManager.getCurrentUser());
        fillGUI();
    }

    /**
     * Handles an ActionEvent on the goBack button by switching to the mainScene.
     *
     * @param event The ActionEvent on the goBack button.
     */
    @FXML
    void goBack(ActionEvent event) {
        windowManager.switchToScene("primaryStage", "mainScene");
    }

    /**
     * Handles an ActionEvent on the goPremium button by opening a pop-up window for
     * upgrading to a premium account.
     *
     * @param event The ActionEvent on the goPremium button.
     */
    @FXML
    void goPremium(ActionEvent event) {
        Stage popUpStage = new Stage();
        popUpStage.initOwner(stage);
        popUpStage.initModality(Modality.WINDOW_MODAL);
        windowManager.addStage("popUpStage", popUpStage);
        windowManager.switchToScene("popUpStage", "premiumUpgradeScene");
        windowManager.getScene("premiumUpgradeScene").addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                System.out.println("ESC1");
                windowManager.removeStage("popUpStage");
            }
        });
    }

    /**
     * Handles an ActionEvent on the logout button by logging out of the current
     * user's account and switching to the loginScene.
     *
     * @param event The ActionEvent on the logout button.
     */
    @FXML
    void logout(ActionEvent event) {
        windowManager.switchToScene("primaryStage", "loginScene");
    }

    /**
     * Handles a MouseEvent on the menuButton by showing the menu.
     *
     * @param event The MouseEvent on the menuButton.
     */
    @FXML
    void showMenu(MouseEvent event) {
        menuButton.show();
    }

    /**
     * Handles a MouseEvent on the menuButton by hiding the menu.
     *
     * @param event The MouseEvent on the menuButton.
     */
    @FXML
    void hideMenu(MouseEvent event) {
        menuButton.hide();
    }

    /**
     * Sets the title of the stage to "Premium registration".
     */
    @Override
    public void setTitle() {
        stage.setTitle("Premium registration");
    }

    /**
     * Fills the GUI with information about the current user and their car.
     */
    @Override
    public void fillGUI() {
        userNameLabel.setText(sessionManager.getCurrentUsername());
        carInformationListView.getItems().clear();
        if (sessionManager.getCurrentUser().getCar() != null) {
            carInformationListView.getItems().addAll(sessionManager.getCurrentUser().getCar().toString());
        }
    }
}
