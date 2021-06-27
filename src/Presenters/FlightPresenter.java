package Presenters;

import java.awt.*;
import java.io.File;

public class FlightPresenter {
    public void displayPassengerMenu() {
        System.out.println("1. Reserve flight");
        System.out.println("2. Cancel flight reservation");
        System.out.println("3. View flight schedule");
        System.out.println("4. Back");
    }

    public void flightReserved() {
        System.out.println("This flight has successfully been reserved.");
    }

    public void cancelledFlight() {
        System.out.println("This flight has successfully been cancelled.");
    }

    public void displayAdminMenu() {
        System.out.println("1. Reserve flight");
        System.out.println("2. Cancel flight reservation");
        System.out.println("3. View attending flight schedule");
        System.out.println("4. Create flight");
        System.out.println("5. Delete flight");
        System.out.println("6. Change flight gate");
        System.out.println("7. View managing flight schedule");
        System.out.println("8. Back");
    }

    public void flightCreated() {
        System.out.println("This flight has successfully been created");
    }

    public void flightDeleted() {
        System.out.println("This flight has successfully been deleted");
    }

    public void gateChanged() {
        System.out.println("The gate for this flight has successfully been changed");
    }

    public void selectFlightToDelete() {
        System.out.println("Please enter the flight number of the flight you would like to delete");
    }

    public void printFlights(String flightsToString) {
        System.out.println(flightsToString);
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void cancelFlightNumber() {
        System.out.println("Please enter the flight number of the flight you would like to cancel");
    }

    public void enterFlightNum() {
        System.out.println("Please enter the flight number of the flight you would like to reserve");
    }

    public void invalidFlightNum() {
        System.out.println("You have entered an invalid flight number");
    }

    public void optionPrompt() {
        System.out.println("Please enter a number representing one of the options above");
    }

    public void optionError() {
        System.out.println("What  you have entered is invalid, please enter a number representing one of the options above");
    }

    public void chooseFlightGate() {
        System.out.println("Please enter the flight number of the flight whose gate you would like to change");
    }

    public void gate() {
        System.out.println("Please enter the gate");
    }

    public void from() {
        System.out.println("Please enter where this flight is departing from");
    }

    public void to() {
        System.out.println("Please enter where this flight is arriving at");
    }

    public void flightNum() {
        System.out.println("Please enter the flight number");
    }

    public void capacity() {
        System.out.println("Please enter the capacity");
    }

    public void year() {
        System.out.println("Please enter the year this flight is scheduled to depart");
    }

    public void month() {
        System.out.println("Please enter the month this flight is schedule to depart");
    }

    public void day() {
        System.out.println("Please enter the day this flight if scheduled to depart");
    }

    public void depHour() {
        System.out.println("Please enter the hour this flight departs at");
    }

    public void dMin() {
        System.out.println("Please enter the minute this flight departs at");
    }

    public void arrivalMin() {
        System.out.println("Please enter the minute that this flight arrives at");
    }

    public void arrivalHour() {
        System.out.println("Please enter the hour that this flight arrives at");
    }

    public void printSchedule(File file) {
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) {
            try {
                desktop.open(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void enterFlightNumTicket() {
        System.out.println("Please enter the flight number of the flight to retrive the ticket");
    }

    public void notOnFlight() {
        System.out.println("You are not on this flight");
    }

    public void printTicket(String toString) {
        System.out.println(toString);
    }
}
