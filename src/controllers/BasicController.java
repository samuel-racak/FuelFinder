package controllers;

import javafx.stage.Stage;
import user.SessionManager;

public abstract class BasicController {
    protected WindowManager windowManager;
    protected SessionManager sessionManager;
    protected Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setWindowManager(WindowManager windowManager) {

        this.windowManager = windowManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public abstract void setTitle();

    public void setUserName() {
        ;
    }
}
