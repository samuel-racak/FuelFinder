package managers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import managers.SessionManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controllers.BasicController;

/**
 * The WindowManager class manages the stages, scenes, and controllers of a
 * JavaFX application.
 */
public class WindowManager {
    /**
     * A map of stage names to Stage objects.
     */
    private Map<String, Stage> stages = new HashMap<>();

    /**
     * A map of scene names to Scene objects.
     */
    private Map<String, Scene> scenes = new HashMap<>();

    /**
     * A map of scene names to BasicController objects.
     */
    private Map<String, ? extends BasicController> controllers = new HashMap<>();

    /**
     * The singleton instance of the WindowManager class.
     */
    private static WindowManager instance;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private WindowManager() {
    }

    /**
     * Returns the singleton instance of the WindowManager class.
     *
     * @return The singleton instance of the WindowManager class.
     */
    public static WindowManager getInstance() {
        if (instance == null) {
            instance = new WindowManager();
        }
        return instance;
    }

    /**
     * Adds a Stage to the stages map with the given name.
     *
     * @param name  The name of the Stage to add.
     * @param stage The Stage to add.
     */
    public void addStage(String name, Stage stage) {
        // check if there is already a stage with the same name
        // if there is an instance use it
        if (stages.get(name) == null) {
            stages.put(name, stage);
        }
    }

    /**
     * Adds a Scene to the scenes map with the given name by loading it from an FXML
     * file and setting its controller's windowManager and sessionManager
     * properties.
     *
     * @param name           The name of the Scene to add.
     * @param fxmlFile       The FXML file to load the Scene from.
     * @param sessionManager The SessionManager to set on the Scene's controller.
     */
    public void addScene(String name, String fxmlFile, SessionManager sessionManager) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/" + fxmlFile));
            Parent root = loader.load();
            BasicController controller = loader.getController();
            controller.setWindowManager(this);
            controller.setSessionManager(sessionManager); // This is the default session manager
            controller.setStage(stages.get("primaryStage")); // This is the default stage name
            Scene scene = new Scene(root);
            scenes.put(name, scene);
            controllers.put(name, loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Switches to a new Scene on a given Stage with a fade out and fade in
     * transition.
     *
     * @param stageName The name of the Stage to switch Scenes on.
     * @param sceneName The name of the Scene to switch to.
     */
    public void switchToScene(String stageName, String sceneName) {

        Scene scene = scenes.get(sceneName);
        Stage stage = stages.get(stageName);

        BasicController controller = controllers.get(sceneName);
        if (controller != null) {
            controller.setTitle();
            controller.fillGUI();
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

    /**
     * Returns a Stage from the stages map with the given name.
     *
     * @param name The name of the Stage to return.
     * @return The Stage with the given name or null if it does not exist in the
     *         stages map.
     */
    public Stage getStage(String name) {
        return stages.get(name);
    }

    /**
     * Removes a Stage from the stages map with the given name and closes it if it
     * exists.
     *
     * @param name The name of the Stage to remove and close.
     */
    public void removeStage(String name) {
        Stage temp = (Stage) stages.get(name);
        if (temp != null) {
            temp.close();
        }

        stages.remove(name);
    }

    /**
     * Returns a Scene from the scenes map with the given name.
     *
     * @param name The name of the Scene to return.
     * @return The Scene with the given name or null if it does not exist in the
     *         scenes map.
     */
    public <T extends Scene> T getScene(String name) {
        return (T) scenes.get(name);
    }

    /**
     * Returns a BasicController from the controllers map with the given name.
     *
     * @param name The name of the BasicController to return.
     * @return The BasicController with the given name or null if it does not exist
     *         in the controllers map.
     */
    public <T extends BasicController> T getController(String name) {
        return (T) controllers.get(name);
    }
}
