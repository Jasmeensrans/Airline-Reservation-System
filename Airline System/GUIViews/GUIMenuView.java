package GUIViews;

import GUIControllers.GUIMenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUIMenuView extends AbstractView{

    GUIMenuController controller;

    public GUIMenuView(GUIMenuController newController) {
        this.controller = newController;
    }

    @Override
    public Scene createScene() {

        Label title = new Label("Main Menu");
        title.setStyle("-fx-font-size: 30.0");
        HBox titleBox = new HBox();
        titleBox.getChildren().addAll(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(6.0, 0.0, 0.0, 0.0));

        Label welcomeBack = new Label("Welcome back, " + controller.getUsername());
        welcomeBack.setStyle("-fx-font-size: 16.0");
        HBox welcomeBackBox = new HBox();
        welcomeBackBox.getChildren().addAll(welcomeBack);
        welcomeBackBox.setAlignment(Pos.CENTER);

        Button flightButton = new Button("My Flights");
        flightButton.setPrefSize(185, 177);
        flightButton.setStyle("-fx-font-size: 16.0");

        Button messagesButton = new Button("My Messages");
        messagesButton.setPrefSize(185, 177);
        messagesButton.setStyle("-fx-font-size: 16.0");

        HBox allButtons = new HBox();
        allButtons.getChildren().addAll(flightButton, messagesButton);
        allButtons.setAlignment(Pos.CENTER);
        allButtons.setPadding(new Insets(19.0, 15.0, 25.0, 15.0));
        allButtons.setSpacing(7.5);

        Button logoutButton = new Button("Log out");
        logoutButton.setPrefSize(300, 43);
        logoutButton.setStyle("-fx-font-size: 16.0");
        HBox logoutButtonBox = new HBox();
        logoutButtonBox.getChildren().addAll(logoutButton);
        logoutButtonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(titleBox, welcomeBackBox, allButtons, logoutButtonBox);
        layout.setPadding(new Insets(15.0, 0.0, 15.0, 0.0));
        
        flightButton.setOnAction(e -> flightButtonClicked());
        messagesButton.setOnAction(e -> messagesButtonClicked());
        logoutButton.setOnAction(e-> logoutButtonClicked());

        Scene scene = new Scene(layout, 600, 429);
        return scene;
    }

    private void logoutButtonClicked() {
        controller.logoutClicked();
        application.setNextScene(controller, controller.run());
    }

    private void messagesButtonClicked() {
        controller.messageClicked();
        application.setNextScene(controller, controller.run());
    }

    private void flightButtonClicked() {
        controller.flightClicked();
        application.setNextScene(controller, controller.run());
    }
}
