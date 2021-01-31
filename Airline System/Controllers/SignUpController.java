package Controllers;

import Presenters.SignUpPresenter;
import Usecases.UserManager;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Scanner;

public class SignUpController extends AbstractController {
    // please enter...
    // make sure usernames are distinct
    // returns menu controller
    private SignUpPresenter view;
    private UserManager um;

    public SignUpController(UseCaseBundle bundle) {
        super(bundle);
        this.um = bundle.getUserManager();
        view = new SignUpPresenter();
    }

    @Override
    public AbstractController run() {
        // first name, last name, DOB, str passport num, int phone num, username, password, email
        Scanner scanner = new Scanner(System.in);
        view.firstName();
        String firstName = scanner.nextLine();
        view.lastName();
        String lastName = scanner.nextLine();

        LocalDate DOB = getDOB(scanner);
        if (DOB == null) {
            return null;
        }
        view.Passport();
        String passport = scanner.nextLine();

        BigInteger phonenum = getPhoneNum(scanner);
        if (phonenum == null) {
            return new EntryController(getBundle());
        }
        scanner.nextLine();
        view.username();
        String username = getUsername(scanner).trim();
        if (username.equals("back")) {
            return new EntryController(getBundle());
        }

        view.password();
        String password = scanner.nextLine();
        view.email();
        String email = scanner.nextLine();
        um.createUser(firstName, lastName, DOB, passport, phonenum, username, password, email, um.Passenger());
        return new MenuController(getBundle(), username);

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
        BigInteger i;
        try {
            view.PhoneNum();
            i = scanner.nextBigInteger();
            return i;
        } catch (Exception e) {
            view.InvalidPhoneNum();
            return null;
        }
    }

    private LocalDate getDOB(Scanner scanner) {
        // want to loop and continue asking for correct dob until valid DOB is entered or type back and program will go back
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
            scanner.nextLine();
            return LocalDate.of(year, month, day);

        } catch (Exception e) {
            view.InvalidDOB();
            return null;
        }
    }
}
