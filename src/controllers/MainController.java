package controllers;

import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import location.PointOfInterest;
import location.Route;

public class MainController extends BasicController {
    private String currentLocation;
    private String destinationLocation;

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
    private ListView<String> routeList;

    @FXML
    private Button logoutButton;

    @FXML
    private MenuButton menuButton;

    @FXML
    private Button setCurrentButton;

    @FXML
    private Button setDestinationButton;

    @FXML
    private Button navigateButton;

    @FXML
    private MenuItem settingsButton;

    @FXML
    private ComboBox<String> strategyChoice;

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

        // Populate the strategy choice box with the available strategies
        strategyChoice.getItems().setAll("Shortest", "Cheapest");
        // Set the default value to be the first strategy
        strategyChoice.setValue("Shortest");

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
        if (listOfLocations.getSelectionModel().getSelectedItem() != null) {
            currentLocationLabel.setText(listOfLocations.getSelectionModel().getSelectedItem());
            currentLocation = listOfLocations.getSelectionModel().getSelectedItem();
            System.out.println(currentLocation);
        }
    }

    @FXML
    void setDestination(ActionEvent event) {
        if (listOfLocations.getSelectionModel().getSelectedItem() != null) {
            destinationLocationLabel.setText(listOfLocations.getSelectionModel().getSelectedItem());
            destinationLocation = listOfLocations.getSelectionModel().getSelectedItem();
            System.out.println(destinationLocation);
        }
    }

    @FXML
    void navigate(ActionEvent event) {
        System.out.println("Navigate button pressed");
        if (currentLocation == null || destinationLocation == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No location selected");
            alert.setContentText("Please select a current location and a destination");
            alert.showAndWait();
            return;
        }
        if (currentLocation.equals(destinationLocation)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Same location selected");
            alert.setContentText("Please select a different destination");
            alert.showAndWait();
            return;
        }

        sessionManager.changeStrategy(strategyChoice.getValue());
        List<Route> result = sessionManager.navigate(currentLocation, destinationLocation,
                sessionManager.getCurrentUser().getCar());
        if (result == null) {
            if (sessionManager.getCurrentUser().getCar() != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No route found");
                alert.setContentText("No route found between " + currentLocation + " and " + destinationLocation);
                alert.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No car selected");
            alert.setContentText("Please select a car in the settings menu");
            alert.showAndWait();
            return;
        }

        result.forEach(route -> System.out.println(route.toString()));
        ObservableList<String> output = FXCollections
                .observableArrayList(result.stream().map(Route::toString).collect(Collectors.toList()));
        routeList.setItems(output);

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

        List<String> pointsOfInterestStrings = sessionManager.getPOIs().stream()
                .map(PointOfInterest::toString).collect(Collectors.toList()); // this changes the list of POIs to a list
                                                                              // of strings
        ObservableList<String> list = FXCollections.observableArrayList(pointsOfInterestStrings);
        listOfLocations.setItems(list);
    }
}
