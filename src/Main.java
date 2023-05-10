import java.util.Optional;

import controllers.SceneManager;
import javafx.application.Application;
import javafx.application.Platform;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.addScene("login", "login.fxml");
        // sceneManager.addScene("register", "register.fxml");
        sceneManager.addScene("register", "register.fxml");
        sceneManager.addScene("premium", "registerPremium.fxml");
        sceneManager.addScene("main", "mainWindow.fxml");
        sceneManager.addScene("settings", "settings.fxml");
        // sceneManager.switchToScene("login");
        // sceneManager.switchToScene("register");
        sceneManager.switchToScene("main");

        // Parent root =
        // FXMLLoader.load(getClass().getResource("/resources/login.fxml"));
        // Scene scene = new Scene(root);
        // scene.getStylesheets().add(getClass().getResource("/resources/login.css").toExternalForm());
        // primaryStage.setTitle("login page");
        // primaryStage.setScene(scene);
        // primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            if (confirmLogout(primaryStage)) {
                primaryStage.close();
                Platform.exit();
            } else {
                event.consume(); // will prevent the app from closing when user clicks cancel
            }
            // logout(primaryStage);
        });
    }

    private boolean confirmLogout(Window window) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initOwner(window); // this will make the alert window the child of the main window
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    // private void logout(Stage primaryStage) {
    // Alert alert = new Alert(AlertType.CONFIRMATION);
    // alert.setTitle("Logout");
    // alert.setHeaderText("You will be logged out");
    // alert.setContentText("Are you sure you want to exit?");

    // if (alert.showAndWait().get() == ButtonType.OK) {
    // System.out.println("Logging out");
    // // TODO some work when logging out
    // primaryStage.close();
    // }

    /*
     * private void logout(ActionEvent event) {
     * Alert alert = new Alert(AlertType.CONFIRMATION);
     * alert.setTitle("Logout");
     * alert.setHeaderText("You will be logged out");
     * alert.setContentText("Are you sure you want to exit?");
     *
     * if (alert.showAndWait().get() == ButtonType.OK) {
     * stage = (Stage) scenePane.getScene().getWindow();
     * System.out.println("Logging out");
     * // do some work when logging out
     * stage.close();
     * }
     * }
     */

    @Override
    public void stop() throws Exception {
        // TODO end methods and safely close application
        System.out.println("closing application");
        Platform.exit();
    }

    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        launch(args);
    }
}
