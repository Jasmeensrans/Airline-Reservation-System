package GUIViews;

import GUIControllers.GUIMenuController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUIMenuView extends AbstractView {

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
        logoutButton.setPrefSize(370, 43);
        logoutButton.setStyle("-fx-font-size: 16.0");
        HBox logoutButtonBox = new HBox();
        logoutButtonBox.getChildren().addAll(logoutButton);
        logoutButtonBox.setAlignment(Pos.CENTER);

        Button userManager = new Button("User Manager");
        userManager.setPrefSize(370, 43);
        logoutButton.setStyle("-fx-font-size: 16.0");
        HBox userManagerButtonBox = new HBox();
        userManagerButtonBox.getChildren().addAll(userManager);
        userManagerButtonBox.setAlignment(Pos.CENTER);

        ComboBox<String> apearrance = new ComboBox<>();
        apearrance.setItems(FXCollections.observableArrayList("dark", "light", "pink", "green"));
        Button save = new Button("Save");
        HBox color = new HBox();
        color.getChildren().addAll(apearrance, save);


        VBox layout = new VBox();

        if (controller.getUsername().equals("admin_account")) {
            layout.getChildren().addAll(titleBox, welcomeBackBox, allButtons, userManagerButtonBox, logoutButtonBox);
        } else {
            layout.getChildren().addAll(titleBox, welcomeBackBox, allButtons, logoutButtonBox);
        }

        layout.setPadding(new Insets(15.0, 0.0, 15.0, 0.0));
        layout.getChildren().addAll(color);

        flightButton.setOnAction(e -> flightButtonClicked());
        messagesButton.setOnAction(e -> messagesButtonClicked());
        logoutButton.setOnAction(e -> logoutButtonClicked());
        userManager.setOnAction(e -> userManagerClicked());
        save.setOnAction(e -> saveButtonClicked(apearrance.getValue()));


        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearance() + ".css");
        return scene;
    }

    private void saveButtonClicked(String value) {
        if (value != null){
            controller.changeAppearance(value);
        }
    }

    private void userManagerClicked() {
        controller.userManagerClicked();
        application.setNextScene(controller, controller.run());
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
