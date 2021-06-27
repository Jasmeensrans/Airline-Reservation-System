package GUIViews;

import GUIControllers.GUIMessageHomeController;
import entities.UserType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUIMessageHomeView extends AbstractView {

    private GUIMessageHomeController controller;
    private boolean hasMessages;
    private String username;

    public GUIMessageHomeView(GUIMessageHomeController newController) {
        this.controller = newController;
        this.hasMessages = controller.hasMessages();
        this.username = controller.getUsername();
    }

    @Override
    public Scene createScene() {
        // Title
        Label title = new Label();
        title.setText("Messages");
        title.setStyle("-fx-font-size: 25.0");

        // Filter Options
//        Button messagesButton = new Button("messages");
//        messagesButton.setDisable(true);
//        messagesButton.setAlignment(Pos.CENTER);
//        messagesButton.setStyle("-fx-font-size: 14.0");
//        messagesButton.setTextFill(Color.BLACK);
//        messagesButton.setPrefWidth(225);
//        messagesButton.setPrefHeight(20);

//        HBox filters = new HBox();
//        filters.setPadding(new Insets(10.0, 0.0, 2.0, 0.0));
//        filters.setAlignment(Pos.CENTER);
//        filters.getChildren().addAll(messagesButton);

        // Back
        Button backButton = new Button("<-");
        backButton.setPrefWidth(50);
        backButton.setPrefHeight(25);

        // Header
        BorderPane header = new BorderPane();
        header.setPadding(new Insets(10.0, 11.0, 10.0, 10.0));
        BorderPane.setAlignment(backButton, Pos.CENTER_LEFT);
        header.setLeft(backButton);
        BorderPane.setAlignment(title, Pos.CENTER);
        header.setTop(title);
//        BorderPane.setAlignment(filters, Pos.CENTER);
//        header.setBottom(filters);

        Button newMessageButton = null;
        // New Message
        if (controller.getType().equals(UserType.ADMIN)) {
            newMessageButton = new Button("+");
            newMessageButton.setPrefWidth(50);
            newMessageButton.setPrefHeight(25);
            BorderPane.setAlignment(newMessageButton, Pos.CENTER_RIGHT);
            header.setCenter(newMessageButton);
            newMessageButton.setOnAction(e-> newMessageButtonClicked());
        }

        // Message flight button
        Button messageFlightButton = new Button("Message Flight");
//        messageFlightButton.setPrefHeight(50);
//        messageFlightButton.setPrefWidth(100);
        BorderPane.setAlignment(messageFlightButton, Pos.TOP_LEFT);
        header.setRight(messageFlightButton);

        VBox messageHome = new VBox();

        if (hasMessages) {
            // Message Threads
            ScrollPane threads = new ScrollPane();
            threads.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

            VBox vbox = new VBox();

            HashMap<String, ArrayList<Integer>> allMessages = getAllMessages();
            for (Map.Entry sendThread : allMessages.entrySet()) {
                String sendThreadName = (String) sendThread.getKey();

                Label name = new Label();
                name.setText(sendThreadName);
                name.setStyle("-fx-font-size: 18.0");

                Label preview = new Label();
                ArrayList<Integer> messageHistory = allMessages.get(sendThreadName);
                Integer messageHistorySize = messageHistory.size();
                preview.setText(getMessagePreview(messageHistory.get(messageHistorySize - 1)));
                preview.setStyle("-fx-font-size: 14.0");

                Button seeMessageButton = new Button(">");
                seeMessageButton.setPrefWidth(50);
                seeMessageButton.setPrefHeight(30);

                VBox info = new VBox();
                info.setPrefWidth(528);
                info.setAlignment(Pos.CENTER_LEFT);
                info.getChildren().addAll(name, preview);

                BorderPane messageThread = new BorderPane();
//                if (controller.getAppearanceMode().equals("dark")) {
//                    messageThread.setId("BorderPane-threads");
//                }
                messageThread.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
                BorderPane.setAlignment(info, Pos.CENTER_LEFT);
                messageThread.setLeft(info);
                BorderPane.setAlignment(seeMessageButton, Pos.CENTER_RIGHT);
                messageThread.setRight(seeMessageButton);

                vbox.getChildren().addAll(messageThread);

                seeMessageButton.setOnAction(e -> seeMessageButtonClicked(name.getText()));
            }
            threads.setContent(vbox);
            messageHome.getChildren().addAll(header, threads);

        } else {
            Label noMessages = new Label();
            noMessages.setText("No messages");
            noMessages.setStyle("-fx-font-size: 20");
            messageHome.getChildren().addAll(header, noMessages);
        }
        messageHome.setAlignment(Pos.TOP_CENTER);

        backButton.setOnAction(e -> backButtonClicked());
        messageFlightButton.setOnAction(e -> messageFlightButtonClicked());


        Scene scene = new Scene(messageHome, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearance() + ".css");


        return scene;
    }

    private void messageFlightButtonClicked() {
        controller.messageFlightClicked();
        application.setNextScene(controller, controller.run());
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void newMessageButtonClicked() {
        controller.newMessageClicked();
        application.setNextScene(controller, controller.run());
    }

    private void seeMessageButtonClicked(String sendTo) {
        controller.seeMessageClicked(sendTo);
        application.setNextScene(controller, controller.run());
    }

    private String getMessagePreview(Integer messageID) {
        return controller.getMessagePreview(messageID);
    }

    private HashMap<String, ArrayList<Integer>> getAllMessages() {
        return controller.getAllMessages();
    }
}
