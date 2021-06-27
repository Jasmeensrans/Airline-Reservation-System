package GUIViews;

import GUIControllers.GUIUserManagerController;
import entities.User;
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


public class GUIUserManagerView extends AbstractView {

    private GUIUserManagerController controller;
    private TableView<UserInfo> table;

    public GUIUserManagerView(GUIUserManagerController controller) {
        this.controller = controller;
    }

    @Override
    public Scene createScene() {
        //title
        Label title = new Label("User Manager");
        title.setStyle("-fx-font-size: 20");
        title.setLayoutY(7);
        title.setLayoutX(250);

        //Username # column
        TableColumn<UserInfo, String> username = new TableColumn<>("Username");
        username.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("username"));
        username.setPrefWidth(130);

        //first name column
        TableColumn<UserInfo, String> firstName = new TableColumn<>("First Name");
        firstName.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("firstName"));
        firstName.setPrefWidth(119);

        //last name column
        TableColumn<UserInfo, String> lastName = new TableColumn<>("Last Name");
        lastName.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("lastName"));
        lastName.setPrefWidth(110);

        table = new TableView();
        table.setPrefHeight(300);
        table.setPrefWidth(359);
        table.setLayoutY(50);
        table.setLayoutX(10);
        table.getColumns().addAll(username, firstName, lastName);
        table.setItems(getUserInfo());

        Button delete = new Button("Delete");
//        delete.setStyle("-fx-font-size: 20");
        delete.setLayoutX(400);
        delete.setLayoutY(100);

        Button backButton = new Button("Back");
        backButton.setLayoutX(10);
        backButton.setLayoutY(400);
//        backButton.setStyle("-fx-font-size: 20");

        Button createUser = new Button("Create Admin Account");
//        createUser.setStyle("-fx-font-size: 20");
        createUser.setLayoutX(400);
        createUser.setLayoutY(150);

        createUser.setOnAction(e -> createUserClicked());
        delete.setOnAction(e -> deleteClicked());
        backButton.setOnAction(e -> backButtonClicked());

        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(title, table, backButton, delete, createUser);
        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearance() + ".css");
        return scene;
    }

    private void deleteClicked() {
        ObservableList<UserInfo> userSelected, allUsers;
        allUsers = table.getItems();
        if(table.getSelectionModel().getSelectedItem() != null){
            UserInfo user = table.getSelectionModel().getSelectedItem();
            controller.deleteUser(user.getUsername());
            allUsers.remove(user);
        }
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void createUserClicked() {
        controller.createUserClicked();
        application.setNextScene(controller, controller.run());
    }

    public ObservableList getUserInfo() {
        ObservableList<UserInfo> users = FXCollections.observableArrayList();
        ArrayList<ArrayList<String>> userFields = controller.getUserArrayList();
        for (ArrayList<String> info : userFields) {
            if (!info.isEmpty() && !info.get(0).equals("admin_account")) {
                users.add(new UserInfo(info.get(0), info.get(1), info.get(2)));
            }
        }
        return users;
    }

    public class UserInfo {
        private String username;
        private String firstName;
        private String lastName;

        public UserInfo(String username, String firstName, String lastName) {
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getUsername() {
            return username;
        }

        public String getLastName() {
            return lastName;
        }
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
