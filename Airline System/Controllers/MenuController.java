package Controllers;

import Presenters.MenuPresenter;
import Usecases.FlightManager;
import Usecases.TicketManager;
import Usecases.UserManager;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuController extends AbstractController {
    private MenuPresenter view;
    private String username;
    private UserManager um;
    private TicketManager tm;
    private FlightManager fm;

    public MenuController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.view = new MenuPresenter();
        this.username = username;
        this.um = getBundle().getUserManager();
        this.tm = getBundle().getTicketManager();
        this.fm = getBundle().getFlightManager();

    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        if (username.equals("admin_account")) {
            view.displayAdminMenu();
            int option;

            option = selectOption(scanner);

            switch (option) {
                case 1: // Flight manager
                    return new FlightController(getBundle(), username);

                case 2: // Message Manager
                    return new MessageController(getBundle(), username);

                case 3: // create employee account
                    if (createEmployee(scanner)) {
                        view.EmployeeCreated();
                    }
                    return null;
                case 4: // delete user
                    if(deleteUser(scanner)){
                        view.userDeleted();
                    }
                    return null;
                case 5: // logout
                    setPopNum(-1);
                    return null;

            }
        } else {
            view.displayMenu();
            int option;

            option = selectOption(scanner);

            switch (option) {
                case 1: // Flight manager
                    return new FlightController(getBundle(), username);

                case 2: // Message Manager
                    return new MessageController(getBundle(), username);
                case 3:
                    setPopNum(-1);
                    return null;
            }
        }
        return null;
    }

    private boolean deleteUser(Scanner scanner) {
        view.printUsers(um.userString());
        view.askForUsername();
        String user = scanner.nextLine();
        if (user.equals("admin_account")){
            view.cannotDelete();
            return false;
        }
        try {
            String user1 = um.getUser(user).getUsername();
            for (Integer i : um.getUser(user1).getTickets()) {
                String flightNum = tm.getTicket(i).getFlightNumber();
                fm.removePassenger(um.getUser(user1), fm.getFlight(flightNum));
            }
            um.deleteUser(um.getUser(user1));
            return true;
        } catch (Exception e) {
            view.printException(e);
            return false;
        }
    }

    private boolean createEmployee(Scanner scanner) {
        view.firstName();
        String firstName = scanner.nextLine();
        view.lastName();
        String lastName = scanner.nextLine();

        LocalDate DOB = getDOB(scanner);
        if (DOB == null) {
            return false;
        }
        scanner.nextLine();
        view.Passport();
        String passport = scanner.nextLine();
        BigInteger phonenum = getPhoneNum(scanner);
        if (phonenum == null) {
            return false;
        }
        scanner.nextLine();
        view.username();
        String username = getUsername(scanner);
        if (username.equals("back")) {
            return false;
        }

        view.password();
        String password = scanner.nextLine();
        view.email();
        String email = scanner.nextLine();
        um.createUser(firstName, lastName, DOB, passport, phonenum, username, password, email, um.Admin());
        return true;

    }

    private String getUsername(Scanner scanner) {
        String username = scanner.nextLine();
        while (um.userExists(username)) {
            view.UsernameAlreadyExists();
            username = scanner.nextLine();
        }
        return username;
    }

    private BigInteger getPhoneNum(Scanner scanner) {
        try {
            view.PhoneNum();
            return scanner.nextBigInteger();
        } catch (Exception e) {
            view.InvalidPhoneNum();
            return null;
        }
    }

    private LocalDate getDOB(Scanner scanner) {
        int year;
        int month;
        int day;
        try {
            view.year();
            year = scanner.nextInt();
            view.month();
            month = scanner.nextInt();
            view.day();
            day = scanner.nextInt();
            return LocalDate.of(year, month, day);

        } catch (Exception e) {
            view.InvalidDOB();
            return null;
        }
    }


    private int selectOption(Scanner scanner) {
        String input;
        do {
            view.askForInput();
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

}
