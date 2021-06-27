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
    private boolean admin = false;

    public GUISignUpController(UseCaseBundle bundle, boolean admin) {
        super(bundle);
        this.um = bundle.getUserManager();
        setControllerType(ControllerType.SIGNUP);
        this.admin = admin;
        if (admin){
            this.username = "admin_account";
        }
    }


    @Override
    public AbstractController run() {
        if (option == 1) { //back
            setPopNum(1);
            return null;
        } if (option == 2) { //create account
            setPopNum(1);
            return new GUIMenuController(getBundle(), username);
        } if(option == 3) { //admin account created
            setPopNum(1);
            return null;
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
        if(admin){
            um.createUser(firstName, lastName, date, passportNum, BigInteger.valueOf(phoneNum), username, password, email, um.Admin());
        } else{
            um.createUser(firstName, lastName, date, passportNum, BigInteger.valueOf(phoneNum), username, password, email, um.Passenger());
            this.username = username;
        }
        return true;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getAppearance(){
        return um.getUser(username).getAppearance();
    }
}
