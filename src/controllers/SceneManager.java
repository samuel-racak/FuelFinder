package controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// this class will be responsible for managing the scenes and controllers
public class SceneManager {
    private Stage stage;
    private Map<String, Scene> scenes = new HashMap<>();
    private Map<String, ? extends BasicController> controllers = new HashMap<>();
    private static SceneManager instance;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager(new Stage());
        }
        return instance;
    }

    // public Stage getStage() {
    // return stage;
    // }

    public void addScene(String name, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/" + fxmlFile));
            Parent root = loader.load();
            BasicController controller = loader.getController();
            controller.setSceneManager(this);
            controller.setStage(stage);
            controller.setTitle();
            Scene scene = new Scene(root);
            scenes.put(name, scene);
            controllers.put(name, loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToScene(String name) {

        Scene scene = scenes.get(name);
        BasicController controller = controllers.get(name);
        controller.setTitle();

        if (scene != null) {

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
                System.out.println("switching to scene: " + name);
                stage.setScene(scene);
                stage.show();
                fadeIn.play();
            });
        }

        // Scene scene = scenes.get(name);
        // if (scene != null) {
        // fadeOut.play();
        // System.out.println("switching to scene: " + name);
        // stage.setScene(scene);
        // stage.show();
        // fadeIn.play();
        // }
    }

    public <T extends BasicController> T getController(String name) {
        return (T) controllers.get(name);
    }
}