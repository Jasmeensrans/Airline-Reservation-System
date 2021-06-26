package GUIControllers;

import Controllers.AbstractController;
import Controllers.ControllerType;
import Controllers.UseCaseBundle;
import Usecases.FlightManager;
import Usecases.TicketManager;
import Usecases.UserManager;

import java.util.ArrayList;

public class GUIUserManagerController extends AbstractController {

    private String username;
    private UserManager um;
    private TicketManager tm;
    private FlightManager fm;
    private int option;


    public GUIUserManagerController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        this.um = getBundle().getUserManager();
        this.tm = getBundle().getTicketManager();
        this.fm = getBundle().getFlightManager();
        setControllerType(ControllerType.USERMANGER);
    }

    @Override
    public AbstractController run() {
        // 1. create admin account, 2. back
        switch (option) {
            case 1:
                setPopNum(1);
                return new GUISignUpController(getBundle(), true);
                // return altered version of sign up controller
            case 2:
                //back
                setPopNum(1);
                return null;
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getUserArrayList() {
        ArrayList<ArrayList<String>> users = new ArrayList<>();
        for (String u : um.getAllUsers()) {
            ArrayList<String> user = new ArrayList<>();
            user.add(u);
            user.add(um.getUser(u).getFirstName());
            user.add(um.getUser(u).getLastName());
            users.add(user);
        }
        return users;
    }

    public void deleteUser(String username) {
        try {
            for (Integer i : um.getUser(username).getTickets()) {
                String flightNum = tm.getTicket(i).getFlightNumber();
                fm.removePassenger(um.getUser(username), fm.getFlight(flightNum));
            }
        } catch(Exception ignored){}
        um.deleteUser(um.getUser(username));
    }

    public void backClicked() {
        option = 2;
    }

    public void createUserClicked() {
        option = 1;
    }
    public String getAppearance(){
        return um.getUser(username).getAppearance();
    }
}
