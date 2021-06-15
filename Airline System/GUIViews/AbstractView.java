package GUIViews;

import GUIApplication.GUIApplication;
import javafx.scene.Scene;

public abstract class AbstractView {

    protected GUIApplication application;


    public void setApplication(GUIApplication application) {
        this.application = application;
    }

    public abstract Scene createScene();
}
