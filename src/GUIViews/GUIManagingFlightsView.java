package GUIViews;

import GUIControllers.GUIFlightController;
import GUIControllers.GUIManagingFlightController;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class GUIManagingFlightsView extends GUIFlightView {

    private GUIManagingFlightController controller;
    private TableView<FlightInfo> table;
    private boolean error = false;

    public GUIManagingFlightsView(GUIManagingFlightController newController) {
        super(new GUIFlightController(newController.getBundle(), newController.getUsername()));
        this.controller = newController;
    }

    @Override
    public Scene createScene() {
        Label title = new Label("Managing Flights");
        title.setStyle("-fx-font-size: 20");
        title.setLayoutX(220);
        title.setLayoutY(10);

        table = generateTable();
        table.setPrefHeight(300);
        table.setPrefWidth(570);
        table.setLayoutY(50);
        table.setLayoutX(10);

        // gate colomn
        TableColumn<FlightInfo, String> gateColumn = new TableColumn<>("Gate");
        gateColumn.setCellValueFactory(new PropertyValueFactory<>("gate"));
        gateColumn.setPrefWidth(70);
        table.getColumns().addAll(gateColumn);
        table.setItems(getFlightInfo(controller.getManagingFlights(), true));

        TextField gate = new TextField();
        gate.setPromptText("Enter Gate");
        gate.setLayoutY(400);
        gate.setLayoutX(400);

        Button changeGate = new Button("Change Gate");
        changeGate.setLayoutX(300);
        changeGate.setLayoutY(400);
        // bind button
        changeGate.setDisable(true);
        changeGate.disableProperty().bind(
                Bindings.createBooleanBinding(() ->
                        gate.getText().trim().isEmpty(), gate.textProperty()
                )

        );

        Button deleteFlight = new Button("Delete Flight");
        deleteFlight.setLayoutX(70);
        deleteFlight.setLayoutY(400);

        Button createFlight = new Button("Create Flight");
        createFlight.setLayoutX(200);
        createFlight.setLayoutY(400);

        Button back = new Button("Back");
        back.setLayoutX(10);
        back.setLayoutY(400);

        back.setOnAction(e -> backButtonClicked());
        createFlight.setOnAction(e -> createFlightClicked());
        changeGate.setOnAction(e -> changeGateClicked(gate.getText()));
        deleteFlight.setOnAction(e -> deleteFlightClicked());

        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(title, table, gate, changeGate, deleteFlight, createFlight, back);

        if (error) {
            //print that this gate is taken somewhere
            // add label to layout
            Label error = new Label("This gate is already booked at this time");
            error.setStyle("-fx-text-fill: red");
            error.setLayoutY(350);
            error.setLayoutX(300);
            layout.getChildren().addAll(error);
        }

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearance() + ".css");

        return scene;
    }

    private void deleteFlightClicked() {
        ObservableList<FlightInfo> flightSelected, allFlights;
        allFlights = table.getItems();
        if(table.getSelectionModel().getSelectedItem() != null){
            FlightInfo flight = table.getSelectionModel().getSelectedItem();
            controller.deleteFlight(flight.getFlightNum());
            allFlights.remove(flight);
        }
    }

    private void changeGateClicked(String gate) {
        ObservableList<FlightInfo> flightSelected, allFlights;
        allFlights = table.getItems();
        if(table.getSelectionModel().getSelectedItem() != null){
            FlightInfo flightInfo = table.getSelectionModel().getSelectedItem();
            if (!controller.gateAvailable(flightInfo.getFlightNum(), gate)) {
                error = true;
                application.setNextScene(controller, null);
            } else {
                controller.changeGate(flightInfo.getFlightNum(), gate);
            }
        }
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void createFlightClicked() {
        controller.createFlight();
        application.setNextScene(controller, controller.run());
    }
}
