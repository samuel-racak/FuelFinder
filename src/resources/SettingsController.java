package resources;

import controllers.BasicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

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
    private MenuItem settingsButton;

    @FXML
    private Label userNameLabel;

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

    }

    @FXML
    void changeUsername(ActionEvent event) {

    }

    @FXML
    void deleteAccount(ActionEvent event) {

    }

    @FXML
    void generateRandomCar(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchToScene("main");
    }

    @FXML
    void goPremium(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void settings(ActionEvent event) {

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
