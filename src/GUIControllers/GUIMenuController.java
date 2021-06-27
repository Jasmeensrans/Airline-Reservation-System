package GUIControllers;

import Controllers.AbstractController;
import Controllers.ControllerType;
import Controllers.UseCaseBundle;
import Usecases.UserManager;

public class GUIMenuController extends AbstractController {

    private String username;

    private int option;
    private UserManager um;

    public GUIMenuController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        setControllerType(ControllerType.MENU);
        this.um = getBundle().getUserManager();
    }

    @Override
    public AbstractController run() {
        //1. Flight manager, 2. Message Manager, 3. Logout, 4. User Manager (Admin account only)
        switch (option) {
            case 1: //Flight manager
                if (um.getUser(username).getType().equals(um.Passenger())) {
                    return new GUIFlightController(getBundle(), username);
                } else {
                    return new GUIAdminFlightController(getBundle(), username);
                }
            case 2: //Message Manager
                return new GUIMessageHomeController(getBundle(), username);
            case 3: //logout
                setPopNum(1);
                return null;

            case 4: //user manager
                return new GUIUserManagerController(getBundle(), username);

        }
        return null;
    }

    public void flightClicked() {
        option = 1;
    }

    public void messageClicked() {
        option = 2;
    }

    public void logoutClicked() {
        option = 3;
    }

    public void userManagerClicked() {
        option = 4;
    }

    public String getUsername() {
        return username;
    }

    public String getAppearance(){
        return um.getUser(username).getAppearance();
    }

    public void changeAppearance(String value) {
        um.getUser(username).setAppearance(value);
    }
}
