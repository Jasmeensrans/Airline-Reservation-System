package GUIViews;

import Controllers.AbstractController;
import GUIControllers.GUICreateFlightController;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GUICreateFlightView extends AbstractView {

    private GUICreateFlightController controller;

    private boolean dateTimeError = false;
    private boolean gateError = false;
    private boolean capacityError = false;
    private boolean flightNumError = false;

    public GUICreateFlightView(GUICreateFlightController controller){
        this.controller = controller;
    }


    @Override
    public Scene createScene() {
        Label title = new Label("Create Flight");
        title.setLayoutX(600);
        title.setLayoutY(10);
        title.setStyle("-fx-font-size: 29.0");

        Label departing_from = new Label("Departing From");
        departing_from.setLayoutX(300);
        departing_from.setLayoutY(83);
        departing_from.setStyle("-fx-font-size: 14.0");

        Label arriving_to = new Label("Arriving To");
        arriving_to.setLayoutX(300);
        arriving_to.setLayoutY(145);
        arriving_to.setStyle("-fx-font-size: 14.0");

        Label flight_number = new Label("Flight Number");
        flight_number.setLayoutX(300);
        flight_number.setLayoutY(208);
        flight_number.setStyle("-fx-font-size: 14.0");

        Label gate = new Label("Gate");
        gate.setLayoutX(300);
        gate.setLayoutY(273);
        gate.setStyle("-fx-font-size: 14.0");

        Label departure_date = new Label("Departure Date");
        departure_date.setLayoutX(900);
        departure_date.setLayoutY(83);
        departure_date.setStyle("-fx-font-size: 14.0");

        Label arrival_date = new Label("Arrival Date");
        arrival_date.setLayoutX(900);
        arrival_date.setLayoutY(143);
        arrival_date.setStyle("-fx-font-size: 14.0");

        Label departureTime = new Label("Departure Time");
        departureTime.setStyle("-fx-font-size: 14.0");
        departureTime.setLayoutX(600);
        departureTime.setLayoutY(83);

        Label arrivalTime = new Label("Arrival Time");
        arrivalTime.setStyle("-fx-font-size: 14.0");
        arrivalTime.setLayoutX(600);
        arrivalTime.setLayoutY(145);

        Label capacity = new Label("Capacity");
        capacity.setLayoutX(900);
        capacity.setLayoutY(208);
        capacity.setStyle("-fx-font-size: 14.0");

        TextField from = new TextField();
        from.setLayoutX(300);
        from.setLayoutY(103);

        TextField to = new TextField();
        to.setLayoutX(300);
        to.setLayoutY(165);

        ComboBox<Integer> dHour = new ComboBox<Integer>();
        Integer hour[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        dHour.setItems(FXCollections.observableArrayList(hour));
        dHour.setLayoutX(600);
        dHour.setLayoutY(103);
        dHour.getSelectionModel().selectFirst();

        ComboBox<Integer> dMin = new ComboBox<>();
        Integer min[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59};
        dMin.setItems(FXCollections.observableArrayList(min));
        dMin.setLayoutX(670);
        dMin.setLayoutY(103);
        dMin.getSelectionModel().selectFirst();

        ComboBox<Integer> aHour = new ComboBox<>();
        aHour.setItems(FXCollections.observableArrayList(hour));
        aHour.setLayoutX(600);
        aHour.setLayoutY(165);
        aHour.getSelectionModel().selectLast();

        ComboBox<Integer> aMin = new ComboBox<>();
        aMin.setItems(FXCollections.observableArrayList(min));
        aMin.setLayoutX(670);
        aMin.setLayoutY(165);
        aMin.getSelectionModel().selectLast();

        TextField flightNum = new TextField();
        flightNum.setLayoutX(300);
        flightNum.setLayoutY(228);

        TextField gateBox = new TextField();
        gateBox.setLayoutX(300);
        gateBox.setLayoutY(293);

        DatePicker depDateBox = new DatePicker(LocalDate.now());
        depDateBox.setLayoutX(900);
        depDateBox.setLayoutY(103);

        DatePicker arrivalDateBox = new DatePicker(LocalDate.now());
        arrivalDateBox.setLayoutX(900);
        arrivalDateBox.setLayoutY(165);

        TextField capacityBox = new TextField();
        capacityBox.setLayoutY(228);
        capacityBox.setLayoutX(900);

        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(900);
        submitButton.setLayoutY(377);
        submitButton.setStyle("-fx-font-size: 15");
        submitButton.setDisable(true);

        submitButton.disableProperty().bind(
                Bindings.createBooleanBinding(() ->
                        from.getText().trim().isEmpty(), from.textProperty()
                )
                        .or(Bindings.createBooleanBinding(() ->
                                        to.getText().trim().isEmpty(), to.textProperty()
                                )
                        )
                        .or(Bindings.createBooleanBinding(() ->
                                        flightNum.getText().trim().isEmpty(), flightNum.textProperty()
                                )
                        )
                        .or(Bindings.createBooleanBinding(() ->
                                        capacityBox.getText().trim().isEmpty(), capacityBox.textProperty()
                                )
                        )
        );


        Button backButton = new Button("Back");
        backButton.setLayoutX(300);
        backButton.setLayoutY(377);

        backButton.setOnAction(e -> backButtonClicked());
        submitButton.setOnAction(e -> submitButtonClicked(flightNum.getText(), from.getText(), to.getText(), arrivalDateBox.getValue(), depDateBox.getValue(), dHour.getValue(), dMin.getValue(), aHour.getValue(), aMin.getValue(),capacityBox.getText(), gateBox.getText()));

        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(title, aHour, aMin, arrivalTime, departureTime, departing_from, arriving_to, flight_number, gate, from, to, flightNum, capacityBox, submitButton, backButton, capacity, arrival_date, depDateBox, departure_date, gateBox, arrivalDateBox, dHour, dMin);

        if(dateTimeError){
            Label timeErrorLabel = new Label("Please enter valid arriving and departing dates and times");
            timeErrorLabel.setStyle("-fx-text-fill: red");
            timeErrorLabel.setLayoutX(600);
            timeErrorLabel.setLayoutY(273);
            layout.getChildren().addAll(timeErrorLabel);
        }

        if(capacityError){
            Label capacityErrorLabel = new Label("Please enter a valid capacity");
            capacityErrorLabel.setStyle("-fx-text-fill: red");
            capacityErrorLabel.setLayoutX(600);
            capacityErrorLabel.setLayoutY(300);
            layout.getChildren().addAll(capacityErrorLabel);
        }

        if(gateError){
            Label gateErrorLabel = new Label("This gate is booked at this departing time");
            gateErrorLabel.setStyle("-fx-text-fill: red");
            gateErrorLabel.setLayoutX(600);
            gateErrorLabel.setLayoutY(330);
            layout.getChildren().addAll(gateErrorLabel);

        }

        if(flightNumError){
            Label flightNumErrorLabel = new Label("This flight number is already being used");
            flightNumErrorLabel.setStyle("-fx-text-fill: red");
            flightNumErrorLabel.setLayoutX(600);
            flightNumErrorLabel.setLayoutY(350);
            layout.getChildren().addAll(flightNumErrorLabel);
        }

        Scene scene = new Scene(layout, 1200, 600);
        scene.getStylesheets().add("resources/" + controller.getAppearance() + ".css");
        return scene;

    }

    private void submitButtonClicked(String flightNum, String from, String to, LocalDate arrival, LocalDate departure, Integer dHour, Integer dMin, Integer aHour, Integer aMin, String capacity, String gate) {
        // validate datetime, capacity, gate and flight number
        try{
            Integer capacityNum = Integer.parseInt(capacity);

        } catch (Exception e){
            capacityError = true;
            application.setNextScene(controller, null );
        }

        LocalDateTime arrivalTime = LocalDateTime.of(arrival, LocalTime.of(aHour, aMin));
        LocalDateTime departureTime = LocalDateTime.of(departure, LocalTime.of(dHour, dMin));
        if(!controller.validateTime(arrivalTime, departureTime)){
            dateTimeError = true;
            application.setNextScene(controller, null);
        }

        if(!controller.flightNumAvailable(flightNum)){
            flightNumError = true;
            application.setNextScene(controller, null);
        }

        if (!controller.gateBooked(gate, departureTime)){
            gateError = true;
            application.setNextScene(controller, null);
        }
        if(controller.validateTime(arrivalTime, departureTime) && controller.flightNumAvailable(flightNum) && controller.gateBooked(gate, departureTime)){
            controller.createFlight(flightNum, from, to, departureTime, arrivalTime, gate, Integer.parseInt(capacity));
            application.setNextScene(controller, controller.run());
        }
    }

    private void backButtonClicked() {
        application.setNextScene(controller, controller.run());
    }
}
