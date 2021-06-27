package Controllers;

import Presenters.FlightPresenter;
import Usecases.FlightManager;
import Usecases.TicketManager;
import Usecases.UserManager;

import java.io.File;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightController extends AbstractController {
    private FlightPresenter view;
    private String username;
    private UserManager um;
    private FlightManager fm;
    private TicketManager tm;


    public FlightController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        this.view = new FlightPresenter();
        this.um = bundle.getUserManager();
        this.fm = bundle.getFlightManager();
        this.tm = bundle.getTicketManager();

    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        if (um.getUser(username).getType().equals(um.Admin())) {
            return AdminRun(scanner);
        } else {
            return PassengerRun(scanner);
        }
    }

    private AbstractController PassengerRun(Scanner scanner) {
        view.displayPassengerMenu();
        int option;
        option = selectOptionPassenger(scanner);
        switch (option) {
            case 1: //reserve flight
                if (reserveFlight(scanner)) {
                    view.flightReserved();
                }
                return null;
            case 2: // cancel flight reservation
                if (cancelFlightReservation(scanner)) {
                    view.cancelledFlight();
                }
                return null;
            case 3: // view flight schedule
                viewFlightSchedule(fm.getFlights(um.getUser(username)));
                return null;
            case 4:
                return new MenuController(getBundle(), username);
        }
        return null;
    }


    private AbstractController AdminRun(Scanner scanner) {
        view.displayAdminMenu();
        int option;
        option = selectOptionAdmin(scanner);
        switch (option) {
            case 1: //reserve flight
                if (reserveFlight(scanner)) {
                    view.flightReserved();
                }
                return null;
            case 2: // cancel flight reservation
                if (cancelFlightReservation(scanner)) {
                    view.cancelledFlight();
                }
                return new FlightController(getBundle(), username);
            case 3: // view attedning flight schedule
                viewFlightSchedule(fm.getFlights(um.getUser(username)));
                return null;
            case 4: //create flight
                if (createFlight(scanner)) {
                    view.flightCreated();
                }
                return null;
            case 5: // delete flight
                if (deleteFlight(scanner)) {
                    view.flightDeleted();
                }
                return null;
            case 6: // change flight gate
                if (changeFlightGate(scanner)) {
                    view.gateChanged();
                }
            case 7: // view managing flight schedule
                viewFlightSchedule(fm.managingFlights(username));
                return null;
            case 8:
                return new MenuController(getBundle(), username);
        }
        return null;
    }


    private boolean deleteFlight(Scanner scanner) {
        // need to delete flight from all flights
        // need to delete tickets of all passengers from all tickets and from their ticket list
        // need to display list of flights this admin is managing -> create function that gets list of flights and
        view.selectFlightToDelete();
        view.printFlights(fm.flightsToString(fm.managingFlights(username)));
        String flightNum = scanner.nextLine();
        try {
            for (String u : fm.getFlight(flightNum).getPassengers()) {
                for (Integer i : um.getUser(u).getTickets()) {
                    if (tm.getTicket(i).getFlightNumber().equals(flightNum)) {
                        tm.deleteTicket(um.getUser(username), tm.getTicket(i));
                        tm.deleteAllTickets(fm.getFlight(flightNum));
                    }
                }
            }
            fm.deleteFlight(fm.getFlight(flightNum), username);
            return true;

        } catch (Exception e) {
            view.printException(e);
            return false;
        }
    }

    private boolean cancelFlightReservation(Scanner scanner) {
        // ask for flight number
        // try to cancel, catch any exceptions, print and return false
        view.cancelFlightNumber();
        String flightNum = scanner.nextLine();
        try {
            ArrayList<Integer> toDelete = new ArrayList<>();
            fm.removePassenger(um.getUser(username), fm.getFlight(flightNum));
            for (Integer i : um.getUser(username).getTickets()) {
                if (tm.getTicket(i).getFlightNumber().equals(flightNum)) {
                    toDelete.add(i);
                }
            }
            for (Integer l : toDelete) {
                tm.deleteTicket(um.getUser(username), tm.getTicket(l));
            }
            return true;
        } catch (Exception e) {
            view.printException(e);
            return false;
        }
    }

    private boolean reserveFlight(Scanner scanner) {
        // display all flights
        // ask to input which flight they want to reserve
        // call can add passenger
        // add passenger to flight
        // create ticket
        if (fm.availableFlights().isEmpty()) {
            return false;
        }
        view.printFlights(fm.flightsToString(fm.availableFlights()));
        view.enterFlightNum();
        String flightNum = scanner.nextLine();
        if (!fm.availableFlights().contains(flightNum)) {
            view.invalidFlightNum();
            return false;
        }
        try {
            tm.canAddPassenger(um.getUser(username), fm.getFlight(flightNum).getArrival(), fm.getFlight(flightNum).getDeparture());
            fm.addPassenger(fm.getFlight(flightNum), um.getUser(username));
            tm.CreateTicket(um.getUser(username), um.getUser(username).getFirstName(), um.getUser(username).getLastName(), flightNum, fm.getFlight(flightNum).getGate(), fm.getFlight(flightNum).getTo(), fm.getFlight(flightNum).getFrom(), fm.getFlight(flightNum).getArrival(), fm.getFlight(flightNum).getDeparture());
            return true;
        } catch (Exception e) {
            view.printException(e);
            return false;
        }

    }


    private int selectOptionPassenger(Scanner scanner) {
        String input;
        do {
            view.optionPrompt();
            input = scanner.nextLine();
            switch (input.trim()) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                case "5":
                    return 5;
            }
            view.optionError();
        } while (true);
    }


    private boolean changeFlightGate(Scanner scanner) {
        view.chooseFlightGate();
        view.printFlights(fm.flightsToString(fm.managingFlights(username)));
        String flightNum = scanner.nextLine();
        view.gate();
        String gate = scanner.nextLine();
        try {
            fm.canChangeGate(fm.getFlight(flightNum), gate, username);
            fm.changeGate(fm.getFlight(flightNum), gate);
            tm.changeGate(flightNum, gate);
            return true;
        } catch (Exception e) {
            view.printException(e);
            return false;
        }
    }


    private boolean createFlight(Scanner scanner) {
        // ask for from, to, flight number, date, time of arrival (hour and min), time of departure (hour and min), gate, capacity
        view.from();
        String from = scanner.nextLine();
        view.to();
        String to = scanner.nextLine();
        view.flightNum();
        String flightNum = scanner.nextLine();
        try {
            LocalDate date = getDate(scanner);
            LocalDateTime arrival = getArrival(scanner, date.getYear(), date.getMonthValue(), date.getDayOfMonth());
            LocalDateTime dep = getDeparture(scanner, date.getYear(), date.getMonthValue(), date.getDayOfMonth());
            scanner.nextLine();
            view.gate();
            String gate = scanner.nextLine();
            int capacity = getCapacity(scanner);
            fm.createFlight(from, to, flightNum, arrival, dep, capacity, date, gate, username);
            return true;

        } catch (Exception e) {
            view.printException(e);
            return false;
        }
    }

    private int getCapacity(Scanner scanner) throws InvalidParameterException {
        try {
            view.capacity();
            return scanner.nextInt();
        } catch (Exception e) {
            throw new InvalidParameterException("You have entered an invalid capacity");
        }
    }

    private LocalDate getDate(Scanner scanner) throws InvalidParameterException {
        try {
            view.year();
            int year = scanner.nextInt();
            view.month();
            int month = scanner.nextInt();
            view.day();
            int day = scanner.nextInt();
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new InvalidParameterException("You have entered an invalid date");
        }
    }

    private LocalDateTime getArrival(Scanner scanner, int year, int month, int day) throws InvalidParameterException {
        try {
            view.arrivalHour();
            int aHour = scanner.nextInt();
            view.arrivalMin();
            int aMin = scanner.nextInt();
            return LocalDateTime.of(year, month, day, aHour, aMin, 0, 0);
        } catch (Exception e) {
            throw new InvalidParameterException("You have entered an invalid arrival time");
        }
    }

    private LocalDateTime getDeparture(Scanner scanner, int year, int month, int day) throws InvalidParameterException {
        try {
            view.depHour();
            int dHour = scanner.nextInt();
            view.dMin();
            int dMin = scanner.nextInt();
            return LocalDateTime.of(year, month, day, dHour, dMin, 0, 0);
        } catch (Exception e) {
            throw new InvalidParameterException("You have entered an invalid arrival time");
        }
    }

    private int selectOptionAdmin(Scanner scanner) {
        String input;
        do {
            view.optionPrompt();
            input = scanner.nextLine();
            switch (input.trim()) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                case "5":
                    return 5;
                case "6":
                    return 6;
                case "7":
                    return 7;
                case "8":
                    return 8;
                case "9":
                    return 9;
            }
            view.optionError();
        } while (true);

    }

    public void viewFlightSchedule(ArrayList<String> flights) {
        File file = fm.createHTML(um.getUser(username), flights);
        view.printSchedule(file);
    }

}
