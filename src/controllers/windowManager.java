package controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import user.SessionManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// this class will be responsible for managing the scenes and controllers
public class WindowManager {
    private Map<String, Stage> stages = new HashMap<>();
    private Map<String, Scene> scenes = new HashMap<>();
    private Map<String, ? extends BasicController> controllers = new HashMap<>();
    private static WindowManager instance;

    // public WindowManager(Stage stage) {
    // // this.stage = stage;
    // }

    public static WindowManager getInstance() {
        if (instance == null) {
            // instance = new WindowManager(new Stage());
            instance = new WindowManager();
        }
        return instance;
    }

    public void addStage(String name, Stage stage) {
        stages.put(name, stage);
    }

    public void addScene(String name, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/" + fxmlFile));
            Parent root = loader.load();
            BasicController controller = loader.getController();
            controller.setWindowManager(this);
            controller.setSessionManager(SessionManager.getInstance()); // This is the default session manager
            controller.setStage(stages.get("primaryStage")); // This is the default stage name
            // controller.setTitle();
            Scene scene = new Scene(root);
            scenes.put(name, scene);
            controllers.put(name, loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToScene(String stageName, String sceneName) {

        Scene scene = scenes.get(sceneName);
        Stage stage = stages.get(stageName);

        BasicController controller = controllers.get(sceneName);
        if (controller != null) {
            controller.setTitle();
            controller.setUserName();
        }

        if (scene != null && stage != null) {
            // create a fade out transition
            FadeTransition fadeOut = new FadeTransition(Duration.millis(500), scene.getRoot());
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.play();

            // create a fade in transition
            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), scene.getRoot());
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            // when fade out transition is finished, switch to the new scene
            fadeOut.setOnFinished(event -> {
                System.out.println("switching to scene: " + sceneName);
                stage.setScene(scene);
                stage.show();
                fadeIn.play();
            });
        }
    }

    // returns primary stage
    public Stage getStage(String name) {
        return stages.get(name);
    }

    public void removeStage(String name) {
        Stage temp = (Stage) stages.get(name);
        if (temp != null) {
            temp.close();
        }

        stages.remove(name);
    }

    public <T extends Scene> T getScene(String name) {
        return (T) scenes.get(name);
    }

    public <T extends BasicController> T getController(String name) {
        return (T) controllers.get(name);
    }
}