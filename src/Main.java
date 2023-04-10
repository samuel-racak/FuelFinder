import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/login.fxml"));
        primaryStage.setTitle("login page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume(); // will prevent the app from closing when user clicks cancel
            logout(primaryStage);
        });
    }

    private void logout(Stage primaryStage) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You will be logged out");
        alert.setContentText("are you sure you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("Logging out");
            // do some work when logging out
            primaryStage.close();
        }
    }

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
