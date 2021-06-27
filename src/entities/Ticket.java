package entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ticket implements Serializable {

    private String firstName;
    private String lastName;
    private String flightNumber;
    private String gate;
    private String to;
    private String from;
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private int numTickets = 1;
    private Integer ticketNumber;

    public Ticket(String firstName, String lastName, String flightNumber, String gate, String to, String from, LocalDateTime arrival, LocalDateTime departure) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.flightNumber = flightNumber;
        this.gate = gate;
        this.to = to;
        this.from = from;
        this.arrival = arrival;
        this.departure = departure;
        this.ticketNumber = numTickets;
        numTickets++;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return "Flight Number: " + flightNumber + "\n" + "From: " + from + "\n" + "To: " + to + "\n" + "Departure Time: " + departure.toString() + "\n" + "Arrival Time: " + arrival.toString() + "\n" + "Gate: " + gate;

    }
}
