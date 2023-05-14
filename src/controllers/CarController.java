package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import vehicle.FuelType;

/**
 * This class is the controller for the car form scene of the application.
 */
public class CarController extends BasicController {
    @FXML
    private TextField yearField;

    @FXML
    private TextField licenseNumberField;

    @FXML
    private TextField modelField;

    @FXML
    private ComboBox<FuelType> fuelTypeBox;

    @FXML
    private TextField fuelConsumptionField;

    @FXML
    private TextField fuelTankCapacityField;

    @FXML
    private TextField currentFuelLevelField;

    @FXML
    private Button submitButton;

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        // Populate the fuel type combo box with the available fuel types
        fuelTypeBox.getItems().setAll(FuelType.values());
        // Set the default value to be the first fuel type
        fuelTypeBox.setValue(FuelType.PETROL);

        // event filter to the fuel type combo box to open it when the enter key
        // is pressed
        fuelTypeBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                fuelTypeBox.show();
            }
        });
    }

    /**
     * Handles the submit button action.
     */
    @FXML
    private void handleSubmitButtonAction() {
        // Get the entered values from the form fields

        if (yearField.getText().isEmpty() ||
                licenseNumberField.getText().isEmpty() ||
                modelField.getText().isEmpty() ||
                fuelTypeBox.getValue() == null ||
                fuelConsumptionField.getText().isEmpty() ||
                fuelTankCapacityField.getText().isEmpty() ||
                currentFuelLevelField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
            return;
        }

        int year = Integer.parseInt(yearField.getText());
        String licenseNumber = licenseNumberField.getText();
        String model = modelField.getText();
        FuelType fuelType = fuelTypeBox.getValue();
        double fuelConsumption = Double.parseDouble(fuelConsumptionField.getText());
        int maximumRange = Integer.parseInt(fuelTankCapacityField.getText());
        int currentFuelLevel = Integer.parseInt(currentFuelLevelField.getText());

        // add the car to current user
        sessionManager.addCarToUser(
                year,
                licenseNumber,
                model,
                fuelType,
                fuelConsumption,
                maximumRange,
                currentFuelLevel);

        // print all values
        System.out.println("Year: " + year);
        System.out.println("License number: " + licenseNumber);
        System.out.println("Model: " + model);
        System.out.println("Fuel type: " + fuelType);
        System.out.println("Fuel consumption: " + fuelConsumption);
        System.out.println("Maximum range: " + maximumRange);
        System.out.println("Current fuel level: " + currentFuelLevel);

        System.out.println("Car created!");

        Stage stage = (Stage) yearField.getScene().getWindow(); // get the current stage
        stage.close(); // close the current window
        windowManager.getController("settingsScene").fillGUI();
    }

    /**
     * Sets the title of the stage.
     */
    @Override
    public void setTitle() {
        stage.setTitle("Car Form");
    }
}
