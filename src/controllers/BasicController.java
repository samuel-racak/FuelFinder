package controllers;

import javafx.stage.Stage;

public abstract class BasicController {
    protected WindowManager sceneManager;
    protected Stage stage;

    public void setSceneManager(WindowManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public abstract void setTitle();
}
