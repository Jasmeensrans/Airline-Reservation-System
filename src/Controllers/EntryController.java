package Controllers;

import Presenters.EntryPresenter;

import java.util.Scanner;

public class EntryController extends AbstractController {
    // welcome to the airline reservation system
    // pleaase enter a number representing one of the options below
    // 1 login
    // sign up

    private EntryPresenter view;

    public EntryController(UseCaseBundle bundle) {
        super(bundle);
        this.view = new EntryPresenter();
    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        view.welcomeMessage();
        view.menu();
        String option = getOption(scanner);
        switch (option) {
            case "quit":
                setPopNum(-2);
                return null;
            case "1":
                // Login
                return new LoginController(getBundle());
            case "2":
                return new SignUpController(getBundle());
        }

        // If we reach here, something has gone wrong so restart this frame in the stack
        return null;
    }


    private String getOption(Scanner scanner) {
        String input;
        input = scanner.nextLine();
        boolean isValid = validInput(input);
        while (!isValid) {
            view.InputError();
            input = scanner.nextLine();
            isValid = validInput(input);
        }
        return input;
    }

    private boolean validInput(String input) {
        return input.equals("quit") || input.equals("1") || input.equals("2");
    }
}
