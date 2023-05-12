package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

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

        listOfLocations.getItems().addAll("Item 1", "Item 2", "Item 3");

        // Add a listener to the selectedItem property of the ListView
        listOfLocations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // This code will be executed whenever the user selects an item in the ListView
            System.out.println("Selected item: " + newValue);
            if (newValue != null) {
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
        // TODO: go to premium upgrade page
    }

    @FXML
    void logout(ActionEvent event) {
        // TODO: logout
        sceneManager.switchToScene("login");
    }

    @FXML
    void setCurrent(ActionEvent event) {
        String text = listOfLocations.getSelectionModel().getSelectedItem();
        System.out.println(text);
        if (text != null) {
            currentLocationLabel.setText(text);
        }

        // currentLocationLabel.setText("set current location");
    }

    @FXML
    void setDestination(ActionEvent event) {
        // destinationLocationLabel.setText("set destination");
        destinationLocationLabel.setText(listOfLocations.getSelectionModel().getSelectedItem());
    }

    @FXML
    void settings(ActionEvent event) {
        // TODO: go to settings page
        sceneManager.switchToScene("settings");
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
}
