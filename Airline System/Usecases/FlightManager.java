package Usecases;

import entities.Flight;
import entities.Ticket;
import entities.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class FlightManager implements Serializable {

    private HashMap<String, Flight> allFlights;

    public FlightManager() {
        allFlights = new HashMap<>();
    }

    public void createFlight(String from, String to, String flightNumber, LocalDateTime arrival, LocalDateTime departure, int capacity, LocalDate date, String gate, String flightManager) throws InvalidParameterException {
        if (!arrival.isAfter(departure)) {
            throw new InvalidParameterException("Please enter a valid arrival time");
        }

        for (Flight f : allFlights.values()) {
            if (f.getArrival().equals(arrival) && f.getGate().equals(gate)) {
                throw new InvalidParameterException("This gate is already booked at this time");
            }
            if (f.getFlightNumber().equals(flightNumber)) {
                throw new InvalidParameterException("This flight number is already being used");
            }
        }

        Flight s = new Flight(from.trim(), to.trim(), flightNumber.trim(), arrival, departure, capacity, date, gate, flightManager.trim());
        allFlights.put(s.getFlightNumber(), s);
    }


    public void addPassenger(Flight f, User u) throws InvalidParameterException {
        if (f.getPassengers().contains(u.getUsername())) {
            throw new InvalidParameterException("This passenger is already on this flight");
        }
        f.getPassengers().add(u.getUsername());
    }


    public Flight getFlight(String flightNum) throws InvalidParameterException {
        if (!allFlights.containsKey(flightNum)) {
            throw new InvalidParameterException("This flight does not exist");

        } else {
            return allFlights.get(flightNum);
        }
    }

    public void canChangeGate(Flight f, String gate, String username) throws InvalidParameterException {
        for (Flight flight : allFlights.values()) {
            if (flight.getArrival().equals(f.getArrival()) && flight.getGate().equals(gate)) {
                throw new InvalidParameterException("This gate is already booked at this time");
            }
        }
        if (!f.getFlightManager().equals(username)) {
            throw new InvalidParameterException("You are not the flight manager of this flight");
        }
    }

    public void changeGate(Flight f, String gate) {
        f.setGate(gate);
    }

    public String flightsToString(ArrayList<String> flights) {
        StringBuilder ans = new StringBuilder();
        for (String f : flights) {
            Flight flight = getFlight(f);
            ans.append(flight.toString()).append("\n");
        }
        return ans.toString();
    }

    public ArrayList<String> availableFlights() {
        ArrayList<String> ans = new ArrayList<>();
        Collection<Flight> flights = allFlights.values();
        ArrayList<Flight> af = new ArrayList<>(flights);
        for (Flight f : af) {
            if (f.getPassengers().size() < f.getCapacity()) {
                ans.add(f.getFlightNumber());
            }
        }
        return ans;
    }

    public ArrayList<String> managingFlights(String u) {
        ArrayList<String> ans = new ArrayList<>();
        Collection<Flight> flights = allFlights.values();
        ArrayList<Flight> af = new ArrayList<>(flights);
        for (Flight f : af) {
            if (f.getFlightManager().equals(u)) {
                ans.add(f.getFlightNumber());
            }
        }
        return ans;
    }

    public void deleteFlight(Flight f, String username) throws InvalidParameterException {
        if (!f.getFlightManager().equals(username)) {
            throw new InvalidParameterException("You are not the flight manager of this flight");
        } else {
            allFlights.remove(f.getFlightNumber());
        }
    }


    public void removePassenger(User u, Flight f) throws InvalidParameterException {
        if (!f.getPassengers().contains(u.getUsername())) {
            throw new InvalidParameterException("This user is not on this flight");
        }
        f.getPassengers().remove(u.getUsername());
    }


    public File createHTML(User u, ArrayList<String> a) {
        // need to organize flights by time for this user
        // need to create hashmap from integer to flight object
        File file = new File("Schedule.html");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter ScheduleHTML = new BufferedWriter(fileWriter);
            ScheduleHTML.write("<html><h1><strong><span style=\"font-family:Arial, Helvetica, sans-serif;" +
                    "text-align:center;color: #28324e;\">Flight Schedule</span></strong></h1>");
            for (String flightNum : a) {
                ScheduleHTML.write("<br/>");
                ScheduleHTML.write("<html><b><span style=\"font-family:Arial, Helvetica, sans-serif;" +
                        "text-align:center;color: #28324e;\">" + getFlight(flightNum).toString()+ "</span></b>");
            }
            ScheduleHTML.close();
        } catch (Exception e) {
            //ignore
        }
        return file;
    }

    public ArrayList<String> getFlights(User u){
        ArrayList<String> ans = new ArrayList<>();
        for(Flight f : allFlights.values()){
            if(f.getPassengers().contains(u.getUsername())){
                ans.add(f.getFlightNumber());
            }
        }
        return ans;
    }
}
