package Usecases;

import entities.Flight;
import entities.Ticket;
import entities.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class TicketManager implements Serializable {

    private HashMap<Integer, Ticket> allTickets;

    public TicketManager() {
        allTickets = new HashMap<>();
    }

    public void CreateTicket(User u, String firstName, String lastName, String flightNumber, String gate, String to, String from, LocalDateTime arrival, LocalDateTime departure) throws InvalidParameterException {
        Ticket t = new Ticket(firstName.trim(), lastName.trim(), flightNumber.trim(), gate.trim(), to.trim(), from.trim(), arrival, departure);
        allTickets.put(t.getTicketNumber(), t);
        if (u.getTickets().contains(t.getTicketNumber())) {
            throw new InvalidParameterException("This ticket has already been added");

        } else {
            u.getTickets().add(t.getTicketNumber());
        }
    }

    public void changeGate(String f, String gate) {
        Collection<Ticket> tickets = allTickets.values();
        ArrayList<Ticket> at = new ArrayList<Ticket>(tickets);
        for (Ticket t : at) {
            if (t.getFlightNumber().equals(f)) {
                t.setGate(gate);
            }
        }
    }


    public Ticket getTicket(Integer ticketNum) throws InvalidParameterException {
        if (!allTickets.containsKey(ticketNum)) {
            throw new InvalidParameterException("This ticket does not exist");
        }
        return allTickets.get(ticketNum);
    }

    public void canAddPassenger(User u, LocalDateTime arrival, LocalDateTime departure) throws InvalidParameterException {
        for (Integer tickNum : u.getTickets()) {
            Ticket t = getTicket(tickNum);
            if ((t.getArrival().isAfter(departure) && t.getArrival().isBefore(arrival)) || t.getDeparture().isAfter(departure) && t.getDeparture().isBefore(arrival)) {
                throw new InvalidParameterException("This user is already on another flight at this time");
            }
        }
    }

    public void deleteTicket(User u, Ticket t) {
        if (!u.getTickets().contains(t.getTicketNumber())) {
            throw new InvalidParameterException("This user is not on this flight ");
        }
        u.getTickets().remove(t.getTicketNumber());
    }

    public void deleteAllTickets(Flight f) {
        Collection<Ticket> tickets = allTickets.values();
        ArrayList<Ticket> at = new ArrayList<Ticket>(tickets);
        for (Ticket t : at) {
            if (t.getFlightNumber().equals(f.getFlightManager())) {
                allTickets.remove(t.getTicketNumber());
            }
        }
    }


}


