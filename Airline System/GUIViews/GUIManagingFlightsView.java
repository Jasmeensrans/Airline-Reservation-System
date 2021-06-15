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
        title.setStyle("-fx-font-size: 30");
        title.setLayoutX(200);
        title.setLayoutY(50);

        table = generateTable();
        table.setPrefHeight(429);
        table.setPrefWidth(600);
        table.setLayoutY(100);

        // gate colomn
        TableColumn<FlightInfo, String> gateColumn = new TableColumn<>("Gate");
        gateColumn.setCellValueFactory(new PropertyValueFactory<>("gate"));
        gateColumn.setPrefWidth(110);
        table.getColumns().addAll(gateColumn);
        table.setItems(getFlightInfo(controller.getManagingFlights(), true));

        TextField gate = new TextField();
        gate.setPromptText("Enter Gate");
        gate.setLayoutY(100);
        gate.setLayoutX(700);

        Button changeGate = new Button("Change Gate");
        changeGate.setLayoutX(900);
        changeGate.setLayoutY(100);
        // bind button
        changeGate.setDisable(true);
        changeGate.disableProperty().bind(
                Bindings.createBooleanBinding(() ->
                        gate.getText().trim().isEmpty(), gate.textProperty()
                )

        );

        Button deleteFlight = new Button("Delete Flight");
        deleteFlight.setStyle("-fx-font-size: 20");
        deleteFlight.setLayoutX(700);
        deleteFlight.setLayoutY(200);

        Button createFlight = new Button("Create Flight");
        createFlight.setStyle("-fx-font-size: 20");
        createFlight.setLayoutX(700);
        createFlight.setLayoutY(300);

        Button back = new Button("Back");
        back.setLayoutX(1100);
        back.setLayoutY(500);

        back.setOnAction(e -> backButtonClicked());
        createFlight.setOnAction(e -> createFlightClicked());
        changeGate.setOnAction(e -> changeGateClicked(gate.getText()));
        deleteFlight.setOnAction(e -> deleteFlightClicked());

        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(title, table, gate, changeGate, deleteFlight, createFlight, back);

        if(error){
            //print that this gate is taken somewhere
            // add label to layout
            Label error = new Label("This gate is already booked at this time");
            error.setStyle("-fx-text-fill: red");
            error.setLayoutY(200);
            error.setLayoutX(900);
            layout.getChildren().addAll(error);
        }

        Scene scene = new Scene(layout, 1200, 600);

        return scene;
    }

    private void deleteFlightClicked() {
        ObservableList<FlightInfo> flightSelected, allFlights;
        allFlights = table.getItems();
        flightSelected = table.getSelectionModel().getSelectedItems();

        for(FlightInfo flight : flightSelected){
            controller.deleteFlight(flight.getFlightNum());
        }
        flightSelected.forEach(allFlights::remove);
    }

    private void changeGateClicked(String gate) {
        ObservableList<FlightInfo> flightSelected, allFlights;
        allFlights = table.getItems();
        flightSelected = table.getSelectionModel().getSelectedItems();

        for(FlightInfo flight : flightSelected){
            if(!controller.gateAvailable(flight.getFlightNum(), gate)){
                error = true;
                application.setNextScene(controller, null);
            } else {
                controller.changeGate(flight.getFlightNum(), gate);
            }
        }
        flightSelected.forEach(allFlights::remove);
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void createFlightClicked(){
        controller.createFlight();
        application.setNextScene(controller, controller.run());
    }
}
