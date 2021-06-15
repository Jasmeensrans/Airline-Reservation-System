package GUIControllers;

import Controllers.AbstractController;
import Controllers.ControllerType;
import Controllers.FlightController;
import Controllers.UseCaseBundle;

public class GUIAdminFlightController extends AbstractController {

    String username;
    int option;

    public GUIAdminFlightController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        setControllerType(ControllerType.ADMINFLIGHT);
    }

    @Override
    public AbstractController run() {
        switch (option){
            case 1: //My Flights
                setPopNum(0);
                return new FlightController(getBundle(), username);
            case 2: //Managing Flights
                setPopNum(0);
                return new GUIManagingFlightController(getBundle(), username);
            case 3: // logout
                setPopNum(1);
                return null;
        }
        return null;
    }

    public void myFlightsClicked(){
        this.option = 1;
    }

    public void managingFlightsClicked(){
        this.option = 2;
    }

    public void backClicked(){
        this.option = 3;
    }
}
