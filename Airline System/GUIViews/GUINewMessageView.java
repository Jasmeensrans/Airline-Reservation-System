package GUIViews;

import GUIControllers.GUINewMessageController;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class GUINewMessageView extends AbstractView {

    private GUINewMessageController controller;
    private boolean error = false;

    public GUINewMessageView(GUINewMessageController newController) {
        this.controller = newController;
    }

    @Override
    public Scene createScene() {

        // make this for sending message to individual user (admin), an entire flight (admin) or to single manager (passenger)
        // only thing to change is the send to field
        // error for invalid username
        // Title
        Label title = new Label();
        title.setText("New Message");
        title.setStyle("-fx-font-size: 25.0");

        // Back
        Button backButton = new Button("<-");
        backButton.setPrefWidth(50);
        backButton.setPrefHeight(25);
        HBox backButtonBox = new HBox();
        backButtonBox.getChildren().addAll(backButton);
        backButtonBox.setAlignment(Pos.CENTER_LEFT);

        // Header
        StackPane header = new StackPane();
        header.getChildren().addAll(title, backButtonBox);
        header.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));

        // Send to username
        Label to = new Label("To:");
        to.setPrefHeight(30);
        to.setPrefWidth(50);
        to.setAlignment(Pos.CENTER);

        VBox sendToArea = new VBox();
        HBox sendToFields = new HBox();
        sendToFields.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        sendToFields.setSpacing(2);
        sendToFields.getChildren().addAll(to);

        // Send
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(75);
        sendButton.setPrefHeight(30);
        sendButton.setDisable(true);

        // Message
        TextField messageField = new TextField();
        messageField.setPrefWidth(525);
        messageField.setPrefHeight(30);

        if (controller.type.equals("adminMessage")) {
            TextField sendToField = new TextField();
            sendToField.setPrefWidth(530);
            sendToField.setPrefHeight(30);
            sendToFields.getChildren().addAll(sendToField);
            sendToField.setPromptText("Enter Username");
            sendButton.setOnAction(e -> sendButtonClicked(sendToField.getText(), messageField.getText()));
        }
        if (controller.type.equals("adminFlight")) {
            ComboBox<String> sendToBox = new ComboBox<>();
            sendToBox.setPrefHeight(30);
            sendToBox.setPrefWidth(530);
            sendToBox.setItems(controller.getAdminFlights());
            sendToBox.setPromptText("Choose flight to message passengers");
            sendToFields.getChildren().addAll(sendToBox);
            sendButton.setOnAction(e -> sendButtonClicked(sendToBox.getValue(), messageField.getText()));
        }
        if (controller.type.equals("passengerFlight")) {
            ComboBox<String> sendToBox = new ComboBox<>();
            sendToBox.setPrefHeight(30);
            sendToBox.setPrefWidth(530);
            sendToBox.setItems(controller.getPassengerFlights());
            sendToBox.setPromptText("Choose flight to message flight manager");
            sendToFields.getChildren().addAll(sendToBox);
            sendButton.setOnAction(e -> sendButtonClicked(sendToBox.getValue(), messageField.getText()));
        }

        sendButton.disableProperty().bind(
                Bindings.createBooleanBinding(() ->
                        messageField.getText().trim().isEmpty(), messageField.textProperty()
                )
        );

        // Send message area
        GridPane sendMessageArea = new GridPane();
        sendMessageArea.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        GridPane.setHalignment(messageField, HPos.LEFT);
        sendMessageArea.add(messageField, 0, 0);
        GridPane.setHalignment(sendButton, HPos.RIGHT);
        sendMessageArea.add(sendButton, 1, 0);

        if (error) {
            Label errorMessage = new Label("Invalid username");
            HBox errorMessageBox = new HBox();
            errorMessageBox.getChildren().addAll(errorMessage);
            errorMessageBox.setAlignment(Pos.CENTER);
            sendToArea.getChildren().addAll(errorMessageBox);
        }

        backButton.setOnAction(e -> backButtonClicked());

        BorderPane newMessageHome = new BorderPane();
        newMessageHome.setTop(header);
        newMessageHome.setCenter(sendToArea);
        BorderPane.setAlignment(sendToArea, Pos.TOP_CENTER);
        newMessageHome.setBottom(sendMessageArea);
        newMessageHome.setLeft(sendToFields);

        Scene scene = new Scene(newMessageHome, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearance() + ".css");
        return scene;
    }

    private void sendButtonClicked(String to, String message) {
        // to could be flight number or message
        // check if username exists
        // call controller function to send correct message
        if (controller.type.equals("adminFlight")) {
            controller.messageFlight(to, message);
        }
        if(controller.type.equals("adminMessage")){
            if(!controller.validateUser(to)){
                error = true;
                application.setNextScene(controller, null);
            } else{
                controller.messageUser(to, message);
            }
        }
        if(controller.type.equals("passengerFlight")){
            controller.messageFlightManager(to, message);
        }
        application.setNextScene(controller, controller.run());
    }

    private void backButtonClicked() {
        application.setNextScene(controller, controller.run());
    }
}
