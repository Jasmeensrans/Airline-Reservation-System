package GUIViews;

import GUIControllers.GUIMainEntryController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class GUIMainEntryView extends AbstractView {

    GUIMainEntryController controller;

    public GUIMainEntryView(GUIMainEntryController controller) {
        this.controller = controller;
    }


    @Override
    public Scene createScene() {

        Label welcome = new Label("Airline Reservation System");
        //welcome.setTextFill(Color.DARKGRAY);
        welcome.setLayoutX(200);
        welcome.setLayoutY(68);
        welcome.setStyle("-fx-font-size: 18.0");

        Button loginButton = new Button("Log In");
        //line may do nothing
        //loginButton.setTextFill(Color.DARKGRAY);
        loginButton.setLayoutX(214);
        loginButton.setLayoutY(195);
        loginButton.setStyle("-fx-font-size: 18.0");

        Button signUpButton = new Button("Sign Up");
        //signUpButton.setTextFill(Color.DARKGRAY);
        signUpButton.setLayoutX(311);
        signUpButton.setLayoutY(195);
        signUpButton.setStyle("-fx-font-size: 18.0");

        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(welcome, loginButton, signUpButton);

        loginButton.setOnAction(e -> loginButtonClicked());
        signUpButton.setOnAction(e -> signUpButtonClicked());

        Scene scene = new Scene(layout, 600, 600);
        // scene.getStylesheets.add(..)
        return scene;
    }

    private void loginButtonClicked() {
        controller.loginClicked();
        application.setNextScene(controller, controller.run());

    }

    private void signUpButtonClicked() {
        controller.signUpClicked();
        application.setNextScene(controller, controller.run());
    }
}