package controllers;

import javafx.fxml.FXMLLoader;
import javafx.print.PrinterAttributes;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// this class will be responsible for managing the scenes and controllers
public class SceneManager {
    private Stage stage;
    private Map<String, Scene> scenes = new HashMap<>();
    private Map<String, Object> controllers = new HashMap<>();
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

    public void addScene(String name, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/" + fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scenes.put(name, scene);
            controllers.put(name, loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToScene(String name) {
        Scene scene = scenes.get(name);
        if (scene != null) {
            stage.setScene(scene);
            stage.show();
        }
    }

    public Object getController(String name) {
        return controllers.get(name);
    }
}