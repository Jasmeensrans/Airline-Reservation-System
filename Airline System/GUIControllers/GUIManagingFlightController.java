package GUIControllers;

import Controllers.AbstractController;
import Controllers.ControllerType;
import Controllers.UseCaseBundle;
import Usecases.FlightManager;
import Usecases.TicketManager;
import Usecases.UserManager;

import java.util.ArrayList;

public class GUIManagingFlightController extends AbstractController {

    private String username;
    private FlightManager fm;
    private UserManager um;
    private TicketManager tm;
    private int option;

    public GUIManagingFlightController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.setControllerType(ControllerType.MANAGINGFLIGHTS);
        this.username = username;
        this.um = getBundle().getUserManager();
        this.fm = getBundle().getFlightManager();
        this.tm = new TicketManager();
    }

    @Override
    public AbstractController run() {
        switch (option){
            case 1: //createFlight
                setPopNum(0);
                return new GUICreateFlightController(getBundle(), username);
            case 2: //back
                setPopNum(1);
                return null;
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<ArrayList<String>> getManagingFlights(){
        // flight number, from, to, ari, dep, gate
        return fm.convertFlights(fm.managingFlights(username), true);
    }

    public void backClicked(){
        option = 2;
    }
    public void createFlight(){
        option = 1;
    }

    public boolean gateAvailable(String flightNum, String gate) {
        return fm.canChangeGate(fm.getFlight(flightNum), gate);
    }

    public void changeGate(String flightNum, String gate) {
        fm.changeGate(fm.getFlight(flightNum), gate);
    }

    public void deleteFlight(String flightNum) {
        for (String u : fm.getFlight(flightNum).getPassengers()) {
            for (Integer i : um.getUser(u).getTickets()) {
                if (tm.getTicket(i).getFlightNumber().equals(flightNum)) {
                    tm.deleteTicket(um.getUser(username), tm.getTicket(i));
                    tm.deleteAllTickets(fm.getFlight(flightNum));
                }
            }
        }
        fm.deleteFlight(fm.getFlight(flightNum), username);
    }
}
