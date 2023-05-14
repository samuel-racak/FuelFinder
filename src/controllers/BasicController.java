package controllers;

import javafx.stage.Stage;
import managers.SessionManager;

/**
 * This class is an abstract base class for all controllers in the application.
 */
public abstract class BasicController {
    protected WindowManager windowManager;
    protected SessionManager sessionManager;
    protected Stage stage;

    /**
     * Sets the stage for this controller.
     *
     * @param stage The stage to set.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the window manager for this controller.
     *
     * @param windowManager The window manager to set.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    /**
     * Sets the session manager for this controller.
     *
     * @param sessionManager The session manager to set.
     */
    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Sets the title of the stage.
     */
    public abstract void setTitle();

    /**
     * Fills the GUI with data. This method does nothing by default and can be
     * overridden by subclasses.
     */
    public void fillGUI() {
        ; // do nothing
    }
}
