import java.util.Optional;

import controllers.WindowManager;
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
        WindowManager windowManager = new WindowManager(primaryStage);
        windowManager.addStage("primaryStage", primaryStage);
        windowManager.addScene("loginScene", "login.fxml");
        windowManager.addScene("registerScene", "register.fxml");
        windowManager.addScene("premiumScene", "registerPremium.fxml");
        windowManager.addScene("mainScene", "mainWindow.fxml");
        windowManager.addScene("settingsScene", "settings.fxml");
        windowManager.addScene("carScene", "car.fxml"); // used to setup a new car
        windowManager.addScene("premiumUpgradeScene", "goPremium.fxml");
        windowManager.addScene("adminScene", "admin.fxml");

        windowManager.switchToScene("primaryStage", "loginScene");
        // windowManager.switchToScene("primaryStage", "mainScene");
        // windowManager.switchToScene("primaryStage", "premiumUpgradeScene");

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

    @Override
    public void stop() throws Exception {
        // TODO: end methods and safely close application
        System.out.println("closing application");
        Platform.exit();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
