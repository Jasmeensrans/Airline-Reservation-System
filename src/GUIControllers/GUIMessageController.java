package GUIControllers;

import Controllers.AbstractController;
import Controllers.ControllerType;
import Controllers.UseCaseBundle;
import Usecases.MessageSystem;
import Usecases.UserManager;
import entities.UserType;

import java.util.ArrayList;
import java.util.HashMap;

public class GUIMessageController extends AbstractController {

    private int option;
    private String username;
    private MessageSystem ms;
    private UserManager um;
    private String sendTo;

    public GUIMessageController(UseCaseBundle bundle, String username, String sendTo) {
        super(bundle);
        setControllerType(ControllerType.MESSAGE);
        this.username = username;
        this.sendTo = sendTo;
        this.ms = getBundle().getMessageSystem();
        this.um = getBundle().getUserManager();
    }

    @Override
    public AbstractController run() {
        if(option == 1){
            setPopNum(1);
        }
        return null;
    }

    public String getSendTo() {
        return sendTo;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return um.getUser(username).getType().equals(UserType.ADMIN);
    }

    public String getSender(Integer messageID) {
        return ms.getMessage(messageID).getFrom();
    }

    public String getMessage(Integer messageID) {
        return ms.getMessage(messageID).getMessage();
    }

    public ArrayList<Integer> getMessagesWith() {
        HashMap<String, ArrayList<Integer>> allMessages = um.getUser(username).getMessages();
        return allMessages.get(this.sendTo);
    }

    public void sendClicked(String message) {
        sendMessage(message);
    }

    private void sendMessage(String message) {
        ms.sendMessage(um.getUser(username), um.getUser(sendTo), message);
    }

    public void backClicked() {
        option = 1;
    }
    public String getAppearance(){
        return um.getUser(username).getAppearance();
    }
}
