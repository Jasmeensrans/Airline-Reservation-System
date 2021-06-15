package GUIViews;

import GUIControllers.GUIFlightController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class GUIFlightView extends AbstractView {

    GUIFlightController controller;
    TableView<FlightInfo> table1;
    TableView<FlightInfo> table2;

    public GUIFlightView(GUIFlightController controller) {

        this.controller = controller;
    }


    @Override
    public Scene createScene() {
        Label flights = new Label("Flights");
        flights.setStyle("-fx-font-size: 35");
        flights.setLayoutY(10);
        flights.setLayoutX(600);

        //available flights
        Label availableFlights = new Label("Available Flights");
        availableFlights.setStyle("-fx-font-size: 20");
        availableFlights.setLayoutX(200);
        availableFlights.setLayoutY(60);

        //your flights
        Label yourFlights = new Label("Your Flights");
        yourFlights.setStyle("-fx-font-size: 20");
        yourFlights.setLayoutX(900);
        yourFlights.setLayoutY(60);

        Button reserveButton = new Button("Reserve");
        reserveButton.setLayoutX(200);
        reserveButton.setLayoutY(535);
        reserveButton.setPrefSize(150, 10);

        Button cancelButton = new Button("Cancel");
        cancelButton.setLayoutX(900);
        cancelButton.setLayoutY(535);
        cancelButton.setPrefSize(150, 10);

        Button backButton = new Button("Back");
        backButton.setLayoutX(10);
        backButton.setLayoutY(535);

        table1 = generateTable();
        table1.setPrefHeight(429);
        table1.setPrefWidth(600);
        table1.setLayoutY(100);
        table1.setItems(getFlightInfo(controller.getAvailableFlightInfo(), false));

        table2 = generateTable();
        table2.setPrefWidth(600);
        table2.setPrefHeight(429);
        table2.setLayoutX(600);
        table2.setLayoutY(100);
        table2.setItems(getFlightInfo(controller.getUserFlightInfo(), false));

        reserveButton.setOnAction(e -> reserveButtonClicked());
        cancelButton.setOnAction(e -> cancelButtonClicked());
        backButton.setOnAction(e -> backButtonClicked());


        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(table1, table2, reserveButton, flights, availableFlights, yourFlights, cancelButton, backButton);
        Scene scene = new Scene(layout, 1200, 600);

        return scene;
    }

    public TableView<FlightInfo> generateTable() {
        //Flight # column
        TableColumn<FlightInfo, String> flightNum = new TableColumn<>("Flight #");
        flightNum.setCellValueFactory(new PropertyValueFactory<>("flight#"));
        flightNum.setPrefWidth(130);

        //from column
        TableColumn<FlightInfo, String> from = new TableColumn<>("From");
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        from.setPrefWidth(119);

        //to column
        TableColumn<FlightInfo, String> to = new TableColumn<>("To");
        to.setCellValueFactory(new PropertyValueFactory<>("to"));
        to.setPrefWidth(110);

        //Dep
        TableColumn<FlightInfo, String> Departure = new TableColumn<>("Departure");
        Departure.setCellValueFactory(new PropertyValueFactory<>("departure"));
        Departure.setPrefWidth(125);

        TableColumn<FlightInfo, String> arrival = new TableColumn<>("Arrival");
        arrival.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        arrival.setPrefWidth(125);
        TableView table = new TableView();
        table.getColumns().addAll(flightNum, from, to, Departure, arrival);
        return table;
    }

    public ObservableList getFlightInfo(ArrayList<ArrayList<String>> flightsFields, boolean manager) {
        ObservableList<FlightInfo> flights = FXCollections.observableArrayList();
        for (ArrayList<String> info : flightsFields) {
            if (!info.isEmpty()) {
                if (manager) {
                    flights.add(new FlightInfo(info.get(0), info.get(1), info.get(2), info.get(3), info.get(4), info.get(5)));
                } else {
                    flights.add(new FlightInfo(info.get(0), info.get(1), info.get(2), info.get(3), info.get(4)));
                }
            }
        }
        return flights;
    }

    private void backButtonClicked() {
        application.setNextScene(controller, controller.run());
    }

    private void cancelButtonClicked() {
        ObservableList<FlightInfo> flightSelected, allUserFlights;
        allUserFlights = table2.getItems();
        flightSelected = table2.getSelectionModel().getSelectedItems();

        for (FlightInfo flight : flightSelected) {
            controller.cancelFlight(flight.getFlightNum());
        }
        flightSelected.forEach(allUserFlights::remove);

    }

    private void reserveButtonClicked() {
        ObservableList<FlightInfo> flightSelected, allFlights;
        allFlights = table1.getItems();
        flightSelected = table1.getSelectionModel().getSelectedItems();

        for (FlightInfo flight : flightSelected) {
            controller.reserveFlight(flight.getFlightNum());
        }
        flightSelected.forEach(allFlights::remove);
    }

    public class FlightInfo {
        private String flightNum;
        private String from;
        private String to;
        private String arrival;
        private String departure;
        private String gate;


        public FlightInfo(String flightNum, String from, String to, String arrival, String departure) {
            this.flightNum = flightNum;
            this.from = from;
            this.to = to;
            this.arrival = arrival;
            this.departure = departure;
        }

        public FlightInfo(String flightNum, String from, String to, String arrival, String departure, String gate) {
            this.flightNum = flightNum;
            this.from = from;
            this.to = to;
            this.arrival = arrival;
            this.departure = departure;
            this.gate = gate;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public String getArrival() {
            return arrival;
        }

        public String getDeparture() {
            return departure;
        }

        public String getFlightNum() {
            return flightNum;
        }

        public void setArrival(String arrival) {
            this.arrival = arrival;
        }

        public void setDeparture(String departure) {
            this.departure = departure;
        }

        public void setFlightNum(String flightNum) {
            this.flightNum = flightNum;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getGate() {
            return gate;
        }

        public void setGate(String gate) {
            this.gate = gate;
        }
    }


}
