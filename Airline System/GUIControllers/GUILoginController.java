package GUIControllers;

import Controllers.AbstractController;
import Controllers.ControllerType;
import Controllers.UseCaseBundle;
import Usecases.UserManager;

public class GUILoginController extends AbstractController {

    private int option;
    private String username;
    private UserManager um;

    public GUILoginController(UseCaseBundle bundle) {
        super(bundle);
        setControllerType(ControllerType.LOGIN);
        this.um = getBundle().getUserManager();
    }

    @Override
    public AbstractController run() {
        if (option == 1) { //sign up
            setPopNum(1);
            return new GUISignUpController(getBundle(), false);
        } else if (option == 2) { //back
            setPopNum(1);
            return null;
        } else if (option == 3) { //account created
            setPopNum(1);
            return new GUIMenuController(getBundle(), username);
        }
        return null;
    }


    public void signUpClicked() {
        option = 1;
    }

    public void backClicked() {
        option = 2;
    }

    public void goodLogin() {
        option = 3;
    }

    public boolean validateCredentials(String username, String password) {
        if (um.userExists(username)) {
            if (um.loginVerify(um.getUser(username), password)) {
                this.username = username;
                return true;
            }
        }
        return false;
    }
}
