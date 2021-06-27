package Controllers;

import Usecases.FlightManager;
import Usecases.MessageSystem;
import Usecases.TicketManager;
import Usecases.UserManager;

import java.util.ArrayList;

public class UseCaseBundle {
    private FlightManager flightManager;
    private MessageSystem messageSystem;
    private TicketManager ticketManager;
    private UserManager userManager;

    public UseCaseBundle() throws ClassNotFoundException {
        FileManager fm = new FileManager();
        ArrayList<Object> usecase = fm.load();
        flightManager = (FlightManager) usecase.get(0);
        messageSystem = (MessageSystem) usecase.get(1);
        ticketManager = (TicketManager) usecase.get(2);
        userManager = (UserManager) usecase.get(3);
    }

    public FlightManager getFlightManager() {
        return flightManager;
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public TicketManager getTicketManager() {
        return ticketManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

}
