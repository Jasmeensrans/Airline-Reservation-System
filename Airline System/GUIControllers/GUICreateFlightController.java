package GUIControllers;

import Controllers.AbstractController;
import Controllers.UseCaseBundle;
import Usecases.FlightManager;
import entities.Flight;

import java.time.LocalDateTime;

public class GUICreateFlightController extends AbstractController {

    private String username;
    private FlightManager fm;


    public GUICreateFlightController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        this.fm = getBundle().getFlightManager();
    }

    @Override
    public AbstractController run() {
        setPopNum(1);
        return null;
    }

    public boolean validateTime(LocalDateTime arrival, LocalDateTime departure){
        return true;
    }

    public boolean flightNumAvailable(String flightNum) {
        return !fm.getAllFlights().containsKey(flightNum);
    }

    public boolean gateBooked(String gate, LocalDateTime departure) {
        for (Flight f : fm.getAllFlights().values()) {
            if (f.getDeparture().equals(departure) && f.getGate().equals(gate)) {
                return false;
            }
        }
        return true;
    }

    public void createFlight(String flightNum, String from, String to, LocalDateTime departureTime, LocalDateTime arrivalTime, String gate, int capacity) {
        fm.createFlight(from, to, flightNum, arrivalTime, departureTime, capacity, departureTime.toLocalDate(), gate, username);
    }
}
