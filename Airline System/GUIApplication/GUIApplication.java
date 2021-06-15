package GUIApplication;

import Controllers.AbstractController;
import Controllers.FileManager;
import Controllers.UseCaseBundle;
import GUIControllers.GUIMainEntryController;
import GUIViews.AbstractView;
import GUIViews.GUIMainEntryView;
import GUIViews.GUIViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Stack;

public class GUIApplication extends Application {

    Stack<AbstractView> viewStack = new Stack<>();

    UseCaseBundle bundle;

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        bundle = new UseCaseBundle();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Airline Reservation System");

        GUIMainEntryController mainEntryController = new GUIMainEntryController(bundle);
        GUIViewFactory viewFactory = new GUIViewFactory();
        AbstractView startView = viewFactory.generateView(mainEntryController);
        startView.setApplication(this);
        viewStack.push(startView);
        window.setScene(startView.createScene());

        window.show();
    }

    @Override
    public void stop() throws Exception {
        FileManager fileManager = new FileManager();
        fileManager.deload(bundle);
    }

    public void setNextScene(AbstractController oldController, AbstractController newController) {
        //Sentinel
        if (viewStack.empty()) {
            window.close();
            return;
        }
        int popNum = oldController.getPopNum();
        for (int i = 0; i < popNum; i++) {
            viewStack.pop();
        }
        //Add new view onto stack
        if (newController != null) {
            GUIViewFactory viewFactory = new GUIViewFactory();
            AbstractView newView = viewFactory.generateView(newController);
            newView.setApplication(this);
            viewStack.push(newView);
        }
        if (!viewStack.empty()) {
            window.setScene(viewStack.peek().createScene());
        }
    }


}