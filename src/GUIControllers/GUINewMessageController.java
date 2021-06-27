package GUIControllers;

import Controllers.AbstractController;
import Controllers.ControllerType;
import Controllers.UseCaseBundle;
import Usecases.FlightManager;
import Usecases.MessageSystem;
import Usecases.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class GUINewMessageController extends AbstractController {

    private String username;
    public String type;
    private UserManager um;
    private FlightManager fm;
    private MessageSystem ms;

    public GUINewMessageController(UseCaseBundle bundle, String username, String type) {
        super(bundle);
        setControllerType(ControllerType.NEWMESSAGE);
        this.username = username;
        this.type = type;
        this.um = getBundle().getUserManager();
        this.fm = getBundle().getFlightManager();
        this.ms = getBundle().getMessageSystem();
    }

    @Override
    public AbstractController run() {
        setPopNum(1);
        return null;
    }

    public ObservableList<String> getAdminFlights() {
        // get list of all flights admin is managing
        ArrayList<String> flights = fm.managingFlights(username);
        return FXCollections.observableArrayList(flights);
    }

    public ObservableList<String> getPassengerFlights() {
        ArrayList<String> flights = fm.getFlights(um.getUser(username));
        return FXCollections.observableArrayList(flights);
    }

    public void messageFlight(String flightNumber, String message) {
        for(String user : fm.getFlight(flightNumber).getPassengers()){
            ms.sendMessage(um.getUser(username), um.getUser(user), message);
        }
    }

    public boolean validateUser(String to) {
        return um.userExists(to);
    }

    public void messageUser(String to, String message) {
        ms.sendMessage(um.getUser(username), um.getUser(to), message);
    }

    public void messageFlightManager(String flightNumber, String message) {
        ms.sendMessage(um.getUser(username), um.getUser(fm.getFlight(flightNumber).getFlightManager()), message);
    }
    public String getAppearance(){
        return um.getUser(username).getAppearance();
    }
}
