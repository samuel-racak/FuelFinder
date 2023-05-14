package controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * This class is the controller for the login scene of the application.
 */
public class LoginController extends BasicController {
    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button adminButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    /**
     * Logs in a user.
     *
     * @param event The event that triggered this method.
     */
    @FXML
    public void login(ActionEvent event) {
        String userName = userNameTextField.getText();
        String password = passwordTextField.getText();
        // call some other thread to check if login info correct ? y -> move to next
        // scene

        if (userName.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login Error");
            alert.setContentText("Please enter username and password");
            alert.showAndWait();
        } else {
            Task<Boolean> loginTask = new Task<>() {
                @Override
                protected Boolean call() {
                    return sessionManager.login(userName, password);
                }
            };

            loginTask.setOnSucceeded(otherEvent -> {
                if (loginTask.getValue()) {
                    System.out.println("user logged in");
                    windowManager.switchToScene("primaryStage", "mainScene");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Login Error");
                    alert.setContentText("Username or password incorrect");
                    alert.showAndWait();
                }
            });

            Thread thread = new Thread(loginTask);
            thread.setDaemon(true);
            thread.start();
        }
    }

    /**
     * Registers a new user.
     *
     * @param event The event that triggered this method.
     */
    @FXML
    public void register(ActionEvent event) {
        System.out.println("user wants to register");
        windowManager.switchToScene("primaryStage", "registerScene");
    }

    /**
     * Goes to the admin page.
     *
     * @param event The event that triggered this method.
     */
    @FXML
    public void goToAdminPage(ActionEvent event) {
        String userName = userNameTextField.getText();
        String password = passwordTextField.getText();

        if (userName.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login Error");
            alert.setContentText("Please enter username and password");
            alert.showAndWait();
        } else {
            if (sessionManager.login(userName, password)) {
                System.out.println("user logged in");
                if (sessionManager.isAdmin()) {
                    windowManager.switchToScene("primaryStage", "adminScene");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Admin Error");
                    alert.setContentText("You are not an admin");
                    alert.showAndWait();
                    fillGUI();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Login Error");
                alert.setContentText("Username or password incorrect");
                alert.showAndWait();
            }
        }
    }

    /**
     * Sets the title of the stage.
     */
    @Override
    public void setTitle() {
        stage.setTitle("Login");
    }

    /**
     * Fills the GUI with data.
     */
    @Override
    public void fillGUI() {
        // clear text fields
        userNameTextField.clear();
        passwordTextField.clear();
    }
}
