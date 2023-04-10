package resources;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLoginScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("login page");
        scene = new Scene(root);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToRegisterScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/register.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("register page");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*
     * @FXML
     * private static AnchorPane mainController;
     *
     * public static AnchorPane getMainController() {
     * return mainController;
     * }
     *
     * public void receiveLoginInfo(String userName, String password) {
     * // check if user in database
     * System.out.println(userName);
     * System.out.println(password);
     * }
     */
}
