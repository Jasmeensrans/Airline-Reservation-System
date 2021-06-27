package GUIViews;

import GUIControllers.GUIMessageController;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

public class GUIMessageView extends AbstractView{

    private String sendTo;
    private String username;
    private GUIMessageController controller;
    private ArrayList<Integer> messageList = new ArrayList<>();
    private boolean error;
    private Background fromMsg = new Background(new BackgroundFill(Color.web("#CEDCD5"), new CornerRadii(10.0), Insets.EMPTY));
    private Background fromMsgDark = new Background(new BackgroundFill(Color.web("#31AD6D"), new CornerRadii(10.0), Insets.EMPTY));
    private Background toMsg = new Background(new BackgroundFill(Color.web("#CBDFBF"), new CornerRadii(10.0), Insets.EMPTY));
    private Background toMsgDark = new Background(new BackgroundFill(Color.web("#63A63A"), new CornerRadii(10.0), Insets.EMPTY));
    private boolean admin;


    public GUIMessageView(GUIMessageController newController) {
        this.controller = newController;
        this.sendTo = controller.getSendTo();
        this.username = controller.getUsername();
        this.admin = controller.isAdmin();
    }

    @Override
    public Scene createScene() {
        // Username banner
        Label banner = new Label();
        banner.setText(sendTo);
        banner.setStyle("-fx-font-size: 25.0");
        banner.setAlignment(Pos.CENTER);

        // Back
        Button backButton = new Button("<-");
        backButton.setPrefWidth(50);
        backButton.setPrefHeight(25);
        HBox backButtonBox = new HBox();
        backButtonBox.getChildren().addAll(backButton);
        backButtonBox.setAlignment(Pos.CENTER_LEFT);

        // Header
        StackPane header = new StackPane();
        header.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        header.getChildren().addAll(banner, backButtonBox);

        // Message History
        ScrollPane messageHistory = new ScrollPane();
        messageHistory.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        messageHistory.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        messageHistory.setPadding(new Insets(10.0, 10.0, 10.0, 17.0));

        GridPane messageOrder = new GridPane();
        messageOrder.setVgap(3.0);

        // Message
        ArrayList<Integer> messages = (ArrayList<Integer>) getMessagesWith().clone();
        // to start from newest to oldest
        Collections.reverse(messages);
        Integer n = messages.size() + 1;

        for (Integer i : messages) {

            // message
            Label message = new Label();
            message.setWrapText(true);
            message.setPadding(new Insets(5.0, 7.0, 5.0, 7.0));
            message.setMaxWidth(450);
            String msg = getMessage(i);
            message.setText(msg);
            message.setStyle("-fx-font-size: 16.0");

            HBox hbox = new HBox();
//            if (controller.getAppearanceMode().equals("dark")) {
//                hbox.setId("hbox-group-threads");
//            }
            hbox.setMinWidth(550);
            hbox.setMaxWidth(550);
            hbox.setPadding(new Insets(4.0, 7.0, 0.0, 7.0));

            // name
            String sender = getSender(i);

            if (sender.equals(username)) {
                // from messages
                message.setBackground(fromMsg);
//                if (controller.getAppearanceMode().equals("dark")) {
//                    message.setBackground(fromMsgDark);
//                }
                hbox.setAlignment(Pos.BASELINE_RIGHT);
            } else {
                // to messages
                message.setBackground(toMsg);
//                if (controller.getAppearanceMode().equals("dark")) {
//                    message.setBackground(toMsgDark);
//                }
                hbox.setAlignment(Pos.BASELINE_LEFT);
            }
            hbox.getChildren().add(message);
            messageOrder.add(hbox, 0, n);
            n--;
        }

        messageHistory.setContent(messageOrder);
        messageHistory.setVvalue(messageHistory.getVmax());

        // Message field
        TextField messageField = new TextField();
        messageField.setPrefWidth(525);
        messageField.setPrefHeight(30);

        // Send
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(75);
        sendButton.setPrefHeight(30);
        sendButton.setDisable(true);
        if(admin) {
            sendButton.disableProperty().bind(
                    Bindings.createBooleanBinding(() ->
                            messageField.getText().trim().isEmpty(), messageField.textProperty()));
        }

        // Send message area
        GridPane messageArea = new GridPane();
        messageArea.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        GridPane.setHalignment(messageField, HPos.LEFT);
        messageArea.add(messageField, 0, 0);
        GridPane.setHalignment(sendButton, HPos.RIGHT);
        messageArea.add(sendButton, 1, 0);

        // Layout
        BorderPane border = new BorderPane();
        border.setTop(header);
        border.setBottom(messageArea);
        border.setCenter(messageHistory);

        sendButton.setOnAction(e -> sendButtonClicked(messageField));
        backButton.setOnAction(e -> backButtonClicked());

        Scene scene = new Scene(border, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearance() + ".css");
        return scene;
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void sendButtonClicked(TextField messageField) {
        controller.sendClicked(messageField.getText());
        application.setNextScene(controller, null );
    }

    private String getMessage(Integer messageID) {
        return controller.getMessage(messageID);
    }

    private String getSender(Integer messageID) {
        return controller.getSender(messageID);
    }

    private ArrayList<Integer> getMessagesWith() {
        return controller.getMessagesWith();
    }
}
