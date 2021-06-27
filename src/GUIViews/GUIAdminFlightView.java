package GUIViews;

import GUIControllers.GUIAdminFlightController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class GUIAdminFlightView extends AbstractView {

    GUIAdminFlightController controller;

    public GUIAdminFlightView(GUIAdminFlightController controller) {
        this.controller = controller;
    }

    @Override
    public Scene createScene() {
        // Flights Label
        Label title = new Label("Flights");
        title.setStyle("-fx-font-size: 30.0");
        HBox titleBox = new HBox();
        titleBox.getChildren().addAll(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(6.0, 0.0, 0.0, 0.0));

        // My Flights button
        Button myFlights = new Button("My Flights");
        myFlights.setStyle("-fx-font-size: 16.0");
        myFlights.setPrefSize(185, 177);
        // set X and Y

        // Managing Flights button
        Button managingFlights = new Button("Managing Flights");
        managingFlights.setStyle("-fx-font-size: 16.0");
        managingFlights.setPrefSize(185, 177);

        HBox allButtons = new HBox();
        allButtons.getChildren().addAll(myFlights, managingFlights);
        allButtons.setAlignment(Pos.CENTER);
        allButtons.setPadding(new Insets(19.0, 15.0, 25.0, 15.0));
        allButtons.setSpacing(7.5);

        // Back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 16.0");
        HBox backButtonBox = new HBox();
        backButtonBox.getChildren().addAll(backButton);
        backButtonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(titleBox, allButtons, backButtonBox);
        layout.setPadding(new Insets(15.0, 0.0, 15.0, 0.0));

        backButton.setOnAction(e -> backButtonClicked());
        myFlights.setOnAction(e -> myFlightsButtonClicked());
        managingFlights.setOnAction(e -> managingFlightsButtonClicked());

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearance() + ".css");

        return scene;
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void myFlightsButtonClicked() {
        controller.myFlightsClicked();
        application.setNextScene(controller, controller.run());
    }

    private void managingFlightsButtonClicked() {
        controller.managingFlightsClicked();
        application.setNextScene(controller, controller.run());
    }
}
