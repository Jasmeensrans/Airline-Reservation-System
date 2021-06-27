package GUIControllers;

import Controllers.AbstractController;
import Controllers.ControllerType;
import Controllers.UseCaseBundle;
import Usecases.FlightManager;
import Usecases.TicketManager;
import Usecases.UserManager;

import java.util.ArrayList;

public class GUIFlightController extends AbstractController {

    private String username;
    private UserManager um;
    private FlightManager fm;
    private TicketManager tm;

    public GUIFlightController(UseCaseBundle bundle, String username) {
        super(bundle);
        setControllerType(ControllerType.FLIGHT);
        this.username = username;
        this.um = getBundle().getUserManager();
        this.fm = getBundle().getFlightManager();
        this.tm = getBundle().getTicketManager();

    }

    @Override
    public AbstractController run() {
        setPopNum(1);
        return null;
    }

    public ArrayList<ArrayList<String>> getAvailableFlightInfo() {
        return fm.convertFlights(fm.getAvailableFlights(um.getUser(username)), false);
    }

    public ArrayList<ArrayList<String>> getUserFlightInfo() {
        return fm.convertFlights(fm.getFlights(um.getUser(username)), false);
    }


    public void cancelFlight(String flightNum) {
        ArrayList<Integer> deleteTicket = new ArrayList<>();
        fm.removePassenger(um.getUser(username), fm.getFlight(flightNum));
        for (Integer i : um.getUser(username).getTickets()) {
            if (tm.getTicket(i).getFlightNumber().equals(flightNum)) {
                deleteTicket.add(i);
            }
        }
        for (Integer n : deleteTicket) {
            tm.deleteTicket(um.getUser(username), tm.getTicket(n));
        }
    }

    public void reserveFlight(String flightNum) {
        try {
            fm.addPassenger(fm.getFlight(flightNum), um.getUser(username));
            tm.CreateTicket(um.getUser(username), um.getUser(username).getFirstName(), um.getUser(username).getLastName(), flightNum, fm.getFlight(flightNum).getGate(), fm.getFlight(flightNum).getTo(), fm.getFlight(flightNum).getFrom(), fm.getFlight(flightNum).getArrival(), fm.getFlight(flightNum).getDeparture());
        } catch(Exception Ignored){}
    }
    public String getAppearance(){
        return um.getUser(username).getAppearance();
    }
}
