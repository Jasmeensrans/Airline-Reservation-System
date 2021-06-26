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
//        Label flights = new Label("Flights");
//        flights.setStyle("-fx-font-size: 35");
//        flights.setLayoutY(10);
//        flights.setLayoutX(600);

        //available flights
        Label availableFlights = new Label("Available Flights");
        availableFlights.setStyle("-fx-font-size: 22");
        availableFlights.setLayoutX(200);
        availableFlights.setLayoutY(10);

        //your flights
        Label yourFlights = new Label("Your Flights");
        yourFlights.setStyle("-fx-font-size: 22");
        yourFlights.setLayoutX(650);
        yourFlights.setLayoutY(10);

        Button reserveButton = new Button("Reserve");
        reserveButton.setLayoutX(200);
        reserveButton.setLayoutY(400);
        reserveButton.setPrefSize(150, 10);

        Button cancelButton = new Button("Cancel");
        cancelButton.setLayoutX(650);
        cancelButton.setLayoutY(400);
        cancelButton.setPrefSize(150, 10);

        Button backButton = new Button("Back");
        backButton.setLayoutX(10);
        backButton.setLayoutY(400);

        table1 = generateTable();
        table1.setPrefHeight(300);
        table1.setPrefWidth(500);
        table1.setLayoutY(70);
        table1.setItems(getFlightInfo(controller.getAvailableFlightInfo(), false));

        table2 = generateTable();
        table2.setPrefWidth(500);
        table2.setPrefHeight(300);
        table2.setLayoutX(500);
        table2.setLayoutY(70);
        table2.setItems(getFlightInfo(controller.getUserFlightInfo(), false));

        reserveButton.setOnAction(e -> reserveButtonClicked());
        cancelButton.setOnAction(e -> cancelButtonClicked());
        backButton.setOnAction(e -> backButtonClicked());


        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(table1, table2, reserveButton, availableFlights, yourFlights, cancelButton, backButton);
        Scene scene = new Scene(layout, 1000, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearance() + ".css");

        return scene;
    }

    public TableView<FlightInfo> generateTable() {
        //Flight # column
        TableColumn<FlightInfo, String> flightNum = new TableColumn<>("Flight #");
        flightNum.setCellValueFactory(new PropertyValueFactory<>("flightNum"));
        flightNum.setPrefWidth(100);

        //from column
        TableColumn<FlightInfo, String> from = new TableColumn<>("From");
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        from.setPrefWidth(100);

        //to column
        TableColumn<FlightInfo, String> to = new TableColumn<>("To");
        to.setCellValueFactory(new PropertyValueFactory<>("to"));
        to.setPrefWidth(50);

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
        if(table2.getSelectionModel().getSelectedItem() != null){
            FlightInfo flightInfo = table2.getSelectionModel().getSelectedItem();
            controller.cancelFlight(flightInfo.getFlightNum());
            allUserFlights.remove(flightInfo);
        }
    }

    private void reserveButtonClicked() {
        ObservableList<FlightInfo> flightSelected, allFlights;
        allFlights = table1.getItems();
        if(table1.getSelectionModel().getSelectedItem() != null){
            FlightInfo flight = table1.getSelectionModel().getSelectedItem();
            controller.reserveFlight(flight.getFlightNum());
            allFlights.remove(flight);
        }
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
