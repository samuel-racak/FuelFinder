package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Key;

public class SettingsController extends BasicController {

    @FXML
    private Button changeUserNameButton;

    @FXML
    private Button deleteAccountButton;

    @FXML
    private Button generateNewCarButton;

    @FXML
    private Button generateRandomCarButton;

    @FXML
    private Button goBackButton;

    @FXML
    private MenuItem goPremiumButton;

    @FXML
    private ImageView imageView;

    @FXML
    private Button logoutButton;

    @FXML
    private MenuButton menuButton;

    @FXML
    private Label userNameLabel;

    @FXML
    private TextField newUserName;

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

    @FXML
    void carCreation(ActionEvent event) {
        sceneManager.switchToScene("carForm");
        // Stage carStage = sceneManager.getStage("car");

        // try {
        // // Load the FXML file
        // FXMLLoader loader = new
        // FXMLLoader(getClass().getResource("/resources/car.fxml"));
        // VBox carForm = loader.load();

        // // Create a new stage for the pop-up window
        // Stage popupStage = new Stage();
        // popupStage.setTitle("Car Form");
        // popupStage.initOwner(stage);
        // popupStage.initModality(Modality.WINDOW_MODAL); // Prevents user from
        // interacting with other windows

        // // Set the scene of the stage to a new scene containing the car form
        // Scene scene = new Scene(carForm);
        // popupStage.setScene(scene);

        // scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
        // if (keyEvent.getCode() == KeyCode.ESCAPE) {
        // popupStage.close();
        // }
        // });

        // // Show the pop-up window
        // popupStage.show();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

    @FXML
    void changeUsername(ActionEvent event) {
        // TODO: set the user name in every scene
        userNameLabel.setText(newUserName.getText());
    }

    @FXML
    void deleteAccount(ActionEvent event) {
        // TODO: delete user and return to login page
        sceneManager.switchToScene("login");
    }

    @FXML
    void generateRandomCar(ActionEvent event) {
        // TODO: generate random car and add it to user
    }

    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchToScene("main");
    }

    @FXML
    void goPremium(ActionEvent event) {
        // TODO: create new page for user to upgrade to premium
    }

    @FXML
    void logout(ActionEvent event) {
        // TODO: update user in UserManager and return to login page
        sceneManager.switchToScene("login");
    }

    @FXML
    void showMenu(MouseEvent event) {
        menuButton.show();
    }

    @FXML
    void hideMenu(MouseEvent event) {
        menuButton.hide();
    }

    @Override
    public void setTitle() {
        stage.setTitle("Premium registration");
    }
}
