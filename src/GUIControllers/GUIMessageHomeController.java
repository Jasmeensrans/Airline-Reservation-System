package GUIControllers;

import Controllers.AbstractController;
import Controllers.ControllerType;
import Controllers.UseCaseBundle;
import Usecases.MessageSystem;
import Usecases.UserManager;
import entities.UserType;

import java.util.ArrayList;
import java.util.HashMap;

public class GUIMessageHomeController extends AbstractController {

    private UserType type;
    private UserManager um;
    private MessageSystem ms;
    private String username;
    private String sendTo;
    private int option;

    public GUIMessageHomeController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.ms = getBundle().getMessageSystem();
        this.um = getBundle().getUserManager();
        this.username = username;
        this.type = um.getUser(username).getType();
        setControllerType(ControllerType.MESSAGEHOME);

    }

    @Override
    public AbstractController run() {
        // 1. see message 2. new message 3. back 4. unread messages
        switch (option) {
            case 1: // see message
                return new GUIMessageController(getBundle(), username, sendTo);
            case 2: // new message
                setPopNum(0);
                return new GUINewMessageController(getBundle(), username, "adminMessage");
            case 3: // back
                setPopNum(1);
                return null;
            case 4: //message flight clicked
                setPopNum(0);
                if (type.equals(um.Passenger())) {
                    return new GUINewMessageController(getBundle(), username, "passengerFlight");
                } else {
                    return new GUINewMessageController(getBundle(), username, "adminFlight");
                }
        }
        return null;
    }

    public UserType getType() {
        return type;
    }

    public HashMap<String, ArrayList<Integer>> getAllMessages() {
        return um.getUser(username).getMessages();
    }

    public String getMessagePreview(Integer messageID) {
        String message = ms.getMessage(messageID).getMessage();
        if (message.length() > 25) {
            return message.substring(0, 24) + "...";
        } else {
            return message;
        }
    }

    public void seeMessageClicked(String sendTo) {
        this.sendTo = sendTo;
        option = 1;
    }

    public void newMessageClicked() {
        option = 2;
    }

    public void backClicked() {
        option = 3;
    }

    public boolean hasMessages() {
        return !getAllMessages().isEmpty();
    }

    public String getUsername() {
        return username;
    }

    public void messageFlightClicked() {
        option = 4;
    }

    public String getAppearance(){
        return um.getUser(username).getAppearance();
    }
}
