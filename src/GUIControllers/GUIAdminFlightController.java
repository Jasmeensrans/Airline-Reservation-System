package GUIControllers;

import Controllers.AbstractController;
import Controllers.ControllerType;
import Controllers.FlightController;
import Controllers.UseCaseBundle;
import Usecases.UserManager;

public class GUIAdminFlightController extends AbstractController {

    String username;
    int option;
    private UserManager um;

    public GUIAdminFlightController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        setControllerType(ControllerType.ADMINFLIGHT);
        this.um = getBundle().getUserManager();
    }

    @Override
    public AbstractController run() {
        switch (option) {
//My Flights
            case 2 -> {
                setPopNum(0);
                return new GUIFlightController(getBundle(), username);
            }
//Managing Flights
            case 1 -> {
                setPopNum(0);
                return new GUIManagingFlightController(getBundle(), username);
            }
// logout
            case 3 -> {
                setPopNum(1);
                return null;
            }
        }
        return null;
    }

    public void myFlightsClicked(){
        this.option = 2;
    }

    public void managingFlightsClicked(){
        this.option = 1;
    }

    public void backClicked(){
        this.option = 3;
    }

    public String getAppearance(){
        return um.getUser(username).getAppearance();
    }
}
