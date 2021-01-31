package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Flight implements Serializable {

    private String flightNumber;
    private String from;
    private String to;
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private LocalDate date;
    private Integer capacity;
    private ArrayList<String> passengers;
    private String gate;
    private String flightManager;

    public Flight(String from, String to, String flightNumber, LocalDateTime arrival, LocalDateTime departure, Integer capacity, LocalDate date, String gate, String flightManager) {
        this.from = from;
        this.to = to;
        this.flightNumber = flightNumber;
        this.arrival = arrival;
        this.departure = departure;
        this.capacity = capacity;
        this.date = date;
        this.gate = gate;
        this.passengers = new ArrayList<>();
        this.flightManager = flightManager;
    }

    public Integer getCapacity() {
        return capacity;
    }


    public ArrayList<String> getPassengers() {
        return passengers;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getFlightManager() {
        return flightManager;
    }

    @Override
    public String toString() {
        return "Flight#: " + flightNumber + " From: " + from + " To: " + to + " Date: " + date.toString() + " Departure Time: " + departure.toString() + " Arrival Time: " + arrival.toString();
    }
}

