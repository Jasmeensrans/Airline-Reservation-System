package GUIControllers;

import Controllers.AbstractController;
import Controllers.ControllerType;
import Controllers.UseCaseBundle;
import Usecases.UserManager;

import java.math.BigInteger;
import java.time.LocalDate;

public class GUISignUpController extends AbstractController {
    private UserManager um;
    private int option;
    private String username;

    public GUISignUpController(UseCaseBundle bundle) {
        super(bundle);
        this.um = bundle.getUserManager();
        setControllerType(ControllerType.SIGNUP);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { //back
            setPopNum(1);
            return null;
        } else if (option == 2) { //create account
            setPopNum(1);
            return new GUIMenuController(getBundle(), username);
        }
        return null;
    }

    public void backClicked() {
        option = 1;
    }

    public void submitClicked() {
        option = 2;
    }

    public boolean validEntries(String firstName, String lastName, LocalDate date, String passportNum, int phoneNum, String username, String password, String email) {
        if (um.userExists(username)) {
            // this username is taken
            return false;
        }
        // is there a way to make phone num be in for sure?
        um.createUser(firstName, lastName, date, passportNum, BigInteger.valueOf(phoneNum), username, password, email, um.Passenger());
        return true;
    }
}
