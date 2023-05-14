import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import controllers.WindowManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

import managers.SessionManager;
import managers.UserManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // set the icon of the application
        primaryStage.getIcons().add(new Image("file:src/resources/icon.png"));

        WindowManager windowManager = WindowManager.getInstance();
        SessionManager sessionManager = SessionManager.getInstance();

        // create admin account
        String dateString = "12.8.1974";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        sessionManager.registerAdmin("kapitan", "kapitan@danko.sk",
                "vylozky", date, "chlap");

        windowManager.addStage("primaryStage", primaryStage);

        windowManager.addScene("loginScene", "login.fxml", sessionManager);
        windowManager.addScene("registerScene", "register.fxml", sessionManager);
        windowManager.addScene("premiumScene", "registerPremium.fxml", sessionManager);
        windowManager.addScene("mainScene", "mainWindow.fxml", sessionManager);
        windowManager.addScene("settingsScene", "settings.fxml", sessionManager);
        windowManager.addScene("carScene", "car.fxml", sessionManager); // used to setup a new car
        windowManager.addScene("premiumUpgradeScene", "goPremium.fxml", sessionManager);
        windowManager.addScene("adminScene", "admin.fxml", sessionManager);

        windowManager.switchToScene("primaryStage", "loginScene");

        primaryStage.setOnCloseRequest(event -> {
            if (confirmLogout(primaryStage)) {
                primaryStage.close();
                Platform.exit();
            } else {
                event.consume(); // will prevent the app from closing when user clicks cancel
            }
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
        System.out.println("closing application and saving users to file");
        UserManager.getInstance().saveToFile("src/resources/users.ser"); // save the users to file
        Platform.exit();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
