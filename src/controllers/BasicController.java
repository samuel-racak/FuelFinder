package controllers;

import javafx.stage.Stage;

public abstract class BasicController {
    protected WindowManager windowManager;
    protected Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public abstract void setTitle();

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}
