package GUIViews;

import GUIControllers.GUISignUpController;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class GUISignUpView extends AbstractView {

    GUISignUpController controller;

    boolean error = false;

    boolean error2 = false;

    public GUISignUpView(GUISignUpController controller) {
        this.controller = controller;
    }

    @Override
    public Scene createScene() {
        Label title = new Label("Sign Up");
        title.setLayoutX(600);
        title.setLayoutY(10);
        title.setStyle("-fx-font-size: 29.0");

        Label firstNameText = new Label("First Name");
        firstNameText.setLayoutX(300);
        firstNameText.setLayoutY(83);
        firstNameText.setStyle("-fx-font-size: 14.0");

        Label lastNameText = new Label("Last Name");
        lastNameText.setLayoutX(300);
        lastNameText.setLayoutY(145);
        lastNameText.setStyle("-fx-font-size: 14.0");

        Label usernameText = new Label("Username");
        usernameText.setLayoutX(300);
        usernameText.setLayoutY(208);
        usernameText.setStyle("-fx-font-size: 14.0");

        Label passwordText = new Label("Password");
        passwordText.setLayoutX(300);
        passwordText.setLayoutY(273);
        passwordText.setStyle("-fx-font-size: 14.0");

        Label dobText = new Label("Date of Birth");
        dobText.setLayoutX(900);
        dobText.setLayoutY(83);
        dobText.setStyle("-fx-font-size: 14.0");

        Label passportNumText = new Label("Passport Number");
        passportNumText.setLayoutX(900);
        passportNumText.setLayoutY(143);
        passportNumText.setStyle("-fx-font-size: 14.0");

        Label phoneNumText = new Label("Phone Number");
        phoneNumText.setLayoutX(900);
        phoneNumText.setLayoutY(208);
        phoneNumText.setStyle("-fx-font-size: 14.0");

        Label emailText = new Label("Email");
        emailText.setLayoutX(900);
        emailText.setLayoutY(273);
        emailText.setStyle("-fx-font-size: 14.0");

        TextField firstName = new TextField();
        firstName.setLayoutX(300);
        firstName.setLayoutY(103);

        TextField lastName = new TextField();
        lastName.setLayoutX(300);
        lastName.setLayoutY(165);

        TextField username = new TextField();
        username.setLayoutX(300);
        username.setLayoutY(228);

        PasswordField password = new PasswordField();
        password.setLayoutX(300);
        password.setLayoutY(293);

        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setLayoutX(900);
        datePicker.setLayoutY(103);

        TextField passport = new TextField();
        passport.setLayoutX(900);
        passport.setLayoutY(165);

        TextField phoneNumber = new TextField();
        phoneNumber.setLayoutY(228);
        phoneNumber.setLayoutX(900);

        TextField email = new TextField();
        email.setLayoutX(900);
        email.setLayoutY(293);

        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(900);
        submitButton.setLayoutY(377);
        submitButton.setStyle("-fx-font-size: 15");
        submitButton.setDisable(true);

        submitButton.disableProperty().bind(
                Bindings.createBooleanBinding(() ->
                        firstName.getText().trim().isEmpty(), firstName.textProperty()
                )
                        .or(Bindings.createBooleanBinding(() ->
                                        lastName.getText().trim().isEmpty(), lastName.textProperty()
                                )
                        )
                        .or(Bindings.createBooleanBinding(() ->
                                        username.getText().trim().isEmpty(), username.textProperty()
                                )
                        )
                        .or(Bindings.createBooleanBinding(() ->
                                        password.getText().trim().isEmpty(), password.textProperty()
                                )
                        )
                        .or(Bindings.createBooleanBinding(() ->
                                        passport.getText().trim().isEmpty(), passport.textProperty()
                                )
                        )
                        .or(Bindings.createBooleanBinding(() ->
                                        phoneNumText.getText().trim().isEmpty(), phoneNumText.textProperty()
                                )
                        )
                        .or(Bindings.createBooleanBinding(() ->
                                        email.getText().trim().isEmpty(), email.textProperty()
                                )
                        )


        );

        Button backButton = new Button("Back");
        backButton.setLayoutX(300);
        backButton.setLayoutY(377);

        AnchorPane layout = new AnchorPane();

        if (error) {
            Label errorMessage = new Label("This username is taken, please try again.");
            errorMessage.setStyle("-fx-text-fill: red");
            layout.getChildren().add(errorMessage);
            errorMessage.setLayoutX(500);
            errorMessage.setLayoutY(228);

        }
        if (error2) {
            Label errorMessage2 = new Label("This phone number is invalid, please try again.");
            errorMessage2.setStyle("-fx-text-fill: red");
            layout.getChildren().add(errorMessage2);
            errorMessage2.setLayoutX(500);
            errorMessage2.setLayoutY(293);
        }


        layout.getChildren().addAll(title, firstNameText, lastNameText, usernameText, passwordText, firstName, lastName, username, password, submitButton, backButton, emailText, email, phoneNumText, phoneNumber, passportNumText, passport, datePicker, dobText);
        submitButton.setOnAction(e -> submitButtonClicked(firstName.getText(), lastName.getText(), datePicker.getValue(), passport.getText(), phoneNumber.getText(), username.getText(), password.getText(), email.getText()));
        backButton.setOnAction(e -> backButtonClicked());

        Scene scene = new Scene(layout, 1200, 600);
        return scene;
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    // validate phone number and validate user?
    private void submitButtonClicked(String firstName, String lastName, LocalDate day, String passportNum, String phoneNumber, String username, String password, String email) {
        // check for valid phone number aswell
        try {
            Integer i = Integer.parseInt(phoneNumber);
        } catch (Exception e) {
            this.error2 = true;
            application.setNextScene(controller, null);
        }
        if (controller.validEntries(firstName, lastName, day, passportNum, Integer.parseInt(phoneNumber), username, password, email)) {
            controller.submitClicked();
            application.setNextScene(controller, controller.run());
        } else {
            this.error = true;
            application.setNextScene(controller, null);
        }
    }
}
