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
        // carInformationListView.getItems().addAll("Item 1", "Item 2", "Item 3", "Item
        // 4", "Item 5", "Item 6", "Item 7");

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

    @FXML
    void generateRandomCar(ActionEvent event) {
        sessionManager.generateRandomCar(sessionManager.getCurrentUser());
        fillGUI();
    }

    @FXML
    void goBack(ActionEvent event) {
        windowManager.switchToScene("primaryStage", "mainScene");
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
    }

    @FXML
    void logout(ActionEvent event) {
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

    @Override
    public void fillGUI() {
        userNameLabel.setText(sessionManager.getCurrentUsername());
        carInformationListView.getItems().clear();
        if (sessionManager.getCurrentUser().getCar() != null) {
            carInformationListView.getItems().addAll(sessionManager.getCurrentUser().getCar().toString());
        }
    }
}
