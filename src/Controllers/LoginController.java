package Controllers;

import Presenters.LoginPresenter;
import Usecases.UserManager;

import java.util.Scanner;

public class LoginController extends AbstractController {
    // please enter your username
    // please enter your password
    // return menu controller

    private LoginPresenter view;
    private UserManager um;

    public LoginController(UseCaseBundle bundle) {
        super(bundle);
        this.um = bundle.getUserManager();
        this.view = new LoginPresenter();
    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        String username = getUsername(scanner);
        if (username.equals("quit")) {
            setPopNum(-2);
            return null;
        } else if (username.equals("sign up")) {
            setPopNum(1);
            return new SignUpController(getBundle());
        }
        boolean goodPassword = verifyPassword(scanner, username);
        if (!goodPassword) {
            setPopNum(1);
            return null;
        }
        setPopNum(1);
        return new MenuController(getBundle(), username);
    }

    private String getUsername(Scanner scanner) {
        boolean valid;
        String usernameIn;
        do {
            view.askForUsername();
            usernameIn = scanner.nextLine();
            valid = um.userExists(usernameIn) || usernameIn.equals("sign up") || usernameIn.equals("quit");
            if (!valid) {
                view.userNotFound();
            }
        }
        while (!valid);
        return usernameIn;

    }

    private boolean verifyPassword(Scanner scanner, String username) {
        boolean valid;
        String passwordIn;
        do {
            view.askForPassword();
            passwordIn = scanner.nextLine();

            if (passwordIn.equals("back")) {
                return false;
            }

            valid = um.loginVerify(um.getUser(username), passwordIn);

            if (!valid) {
                view.incorrectPassword();
            }
        }
        while (!valid);
        return true;
    }
}
