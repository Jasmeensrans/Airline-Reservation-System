package Controllers;

import Presenters.MessagePresenter;
import Usecases.FlightManager;
import Usecases.MessageSystem;
import Usecases.TicketManager;
import Usecases.UserManager;

import java.util.Scanner;

public class MessageController extends AbstractController {
    // passenger menu
    // 1. view messaging history -> it will give list of flight numbers/ manager to chose from
    // 2. message flight manager

    // admin menu
    // 1. Message flight
    // 2. Message user -> must be attending a flight you are managing
    // 2. View messaging history

    private String username;
    private MessagePresenter view;
    private UserManager um;
    private MessageSystem ms;
    private FlightManager fm;
    private TicketManager tm;

    public MessageController(UseCaseBundle usecase, String username) {
        super(usecase);
        this.username = username;
        this.view = new MessagePresenter();
        this.um = getBundle().getUserManager();
        this.ms = getBundle().getMessageSystem();
        this.fm = getBundle().getFlightManager();
        this.tm = getBundle().getTicketManager();
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

    private AbstractController AdminRun(Scanner scanner) {
        view.displayAdminMenu();
        int option;
        option = selectOptionAdmin(scanner);
        switch (option) {
            case 1: // message flight
                if (messageFlight(scanner)) {
                    view.flightMessaged();
                }
                return null;
            case 2: // message user
                messageUser(scanner);
                return null;
            case 3: // view messaging history
                viewMessagingHistory(scanner);
                return null;
            case 4: // back
                return new MenuController(getBundle(), username);
        }
        return null;
    }

    private AbstractController PassengerRun(Scanner scanner) {
        view.displayPassengerMenu();
        int option;
        option = selectOptionPassenger(scanner);
        switch (option) {
            case 1: //view messaging history
                viewMessagingHistory(scanner);
                return null;
            case 2: // message flight manager
                if (messageFlightManager(scanner)) {
                    view.messageSent();
                }
                return null;
            case 3: // back
                return new MenuController(getBundle(), username);
        }
        return null;
    }

    private boolean messageFlight(Scanner scanner) {
        view.printFlights(fm.flightsToString(fm.managingFlights(username)));
        view.selectFlight();
        String flightNum = scanner.nextLine();
        view.enterMessage();
        String message = scanner.nextLine();
        try {
            if (!fm.getFlight(flightNum).getFlightManager().equals(username)) {
                view.notFlightManager();
                return false;
            } else {
                ms.massMessage(um.getUser(username), um.convertToUser(fm.getFlight(flightNum).getPassengers()), message);
                return true;
            }
        } catch (Exception e) {
            view.printException(e);
            return false;
        }
    }

    private void messageUser(Scanner scanner) {
        view.enterUsername();
        String to = scanner.nextLine();
        view.enterMessage();
        String message = scanner.nextLine();
        try {
            ms.sendMessage(um.getUser(username), um.getUser(to), message);
        } catch (Exception e) {
            view.printException(e);
        }
    }

    private void viewMessagingHistory(Scanner scanner) {
        try {
            view.UserMessagingHistory();
            String from = scanner.nextLine();
            view.printMessage(ms.getMessagingHistory(um.getUser(from), um.getUser(username)));
        } catch (Exception e) {
            view.printException(e);
        }
    }


    private boolean messageFlightManager(Scanner scanner) {
        try {
            view.printFlights(fm.flightsToString(fm.getFlights(um.getUser(username))));
            view.enterFlightNum();
            String flightNum = scanner.nextLine();
            if (!fm.getFlights(um.getUser(username)).contains(flightNum)) {
                view.userNotOnFLight();
                return false;
            } else {
                view.enterMessage();
                String message = scanner.nextLine();
                ms.sendMessage(um.getUser(username), um.getUser(fm.getFlight(flightNum).getFlightManager()), message);
                return true;
            }
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
            }
            view.optionError();
        } while (true);
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
            }
            view.optionError();
        } while (true);
    }
}
