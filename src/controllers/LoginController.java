package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends BasicController {
    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @Override
    public void setTitle() {
        stage.setTitle("Login");
    }

    @FXML
    public void login(ActionEvent event) {
        String userName = userNameTextField.getText();
        String password = passwordTextField.getText();
        // call some other thread to check if login info correct ? y -> move to next
        // scene
        System.out.println("handle login");
        System.out.println("----------");
        System.out.println(userName);
        System.out.println(password);
        System.out.println("----------");
        // TODO handle login
    }

    @FXML
    public void register(ActionEvent event) {
        System.out.println("user wants to register");
        windowManager.switchToScene("primaryStage", "registerScene");
        // TODO go to other scene
    }
}