package controllers;

import javafx.stage.Stage;

public abstract class BasicController {
    protected SceneManager sceneManager;
    protected Stage stage;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public abstract void setTitle();
}
