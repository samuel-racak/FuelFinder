package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private ListView<String> carInformationListView;

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

        carInformationListView.getItems().addAll("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7");

    }

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

    @FXML
    void changeUsername(ActionEvent event) {
        // TODO: set the user name in every scene
        userNameLabel.setText(newUserName.getText());
    }

    @FXML
    void deleteAccount(ActionEvent event) {
        // TODO: delete user and return to login page
        windowManager.switchToScene("primaryStage", "loginScene");
        // sceneManager.switchToScene("login");
    }

    @FXML
    void generateRandomCar(ActionEvent event) {
        // TODO: generate random car and add it to user
    }

    @FXML
    void goBack(ActionEvent event) {
        windowManager.switchToScene("primaryStage", "mainScene");
        // sceneManager.switchToScene("main");
    }

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
        // windowManager.switchToScene("popUpStage", "premiumUpgradeScene");

        // TODO: create new page for user to upgrade to premium
    }

    @FXML
    void logout(ActionEvent event) {
        // TODO: update user in UserManager and return to login page
        windowManager.switchToScene("primaryStage", "loginScene");
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
