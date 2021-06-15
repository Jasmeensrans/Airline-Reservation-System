package GUIControllers;

import Controllers.AbstractController;
import Controllers.ControllerType;
import Controllers.UseCaseBundle;

public class GUIMainEntryController extends AbstractController {

    private int option;


    public GUIMainEntryController(UseCaseBundle bundle) {
        super(bundle);
        setControllerType(ControllerType.MAINENTRY);
    }

    @Override
    public AbstractController run() {
        if(option == 1){ //login
            System.out.println("Login selected");
            return new GUILoginController(getBundle());
        }
        else if(option == 2){ //signup
            System.out.println("Sign Up selected");
            return new GUISignUpController(getBundle());
        }
        // if we reach here, something has gone wrong so we restart the program
        setPopNum(-2);
        return null;
    }

    public void loginClicked(){
        option = 1;
    }

    public void signUpClicked(){
        option = 2;
    }

}