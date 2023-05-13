package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController extends BasicController {
    @FXML
    private Label currentLocationLabel;

    @FXML
    private Label destinationLocationLabel;

    @FXML
    private MenuItem goPremiumButton;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<String> listOfLocations;

    @FXML
    private Button logoutButton;

    @FXML
    private MenuButton menuButton;

    @FXML
    private Button setCurrentButton;

    @FXML
    private Button setDestinationButton;

    @FXML
    private MenuItem settingsButton;

    @FXML
    private Label userNameLabel;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label nameField;

    @FXML
    private Label Field;

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

        // TODO: get list of locations from sessionManager
        listOfLocations.getItems().addAll("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7",
                "Item 8", "Item 9", "Item 10", "Item 11", "Item 12", "Item 13", "Item 14", "Item 15", "Item 16",
                "Item 17", "Item 18", "Item 19", "Item 20", "Item 21", "Item 22", "Item 23", "Item 24", "Item 25",
                "Item 26", "Item 27", "Item 28", "Item 29", "Item 30", "Item 31", "Item 32", "Item 33", "Item 34");

        // Add a listener to the selectedItem property of the ListView
        listOfLocations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // This code will be executed whenever the user selects an item in the ListView
            if (newValue != null) {
                System.out.println("Selected item: " + newValue);
                // nameField.setText(newValue.getName());
                // addressField.setText(newValue.getAddress());
                // ratingField.setText(String.valueOf(newValue.getRating()));
            } else {
                // nameField.clear();
                // addressField.clear();
                // ratingField.clear();
            }

        });
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
    void setCurrent(ActionEvent event) {
        // TODO: set current location

        if (listOfLocations.getSelectionModel().getSelectedItem() != null) {
            currentLocationLabel.setText(listOfLocations.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void setDestination(ActionEvent event) {
        // TODO: set destination
        if (listOfLocations.getSelectionModel().getSelectedItem() != null) {
            destinationLocationLabel.setText(listOfLocations.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void settings(ActionEvent event) {
        windowManager.switchToScene("primaryStage", "settingsScene");
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
        stage.setTitle("Main");
    }

    @Override
    public void fillGUI() {
        userNameLabel.setText(sessionManager.getCurrentUsername());
    }
}
