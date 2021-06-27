package Usecases;

import entities.Message;
import entities.User;
import entities.UserType;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

public class MessageSystem implements Serializable {

    private HashMap<Integer, Message> allMessages;
    private Integer numMessages = 1;


    public MessageSystem() {
        allMessages = new HashMap<>();
    }

    public Message getMessage(Integer i) throws InvalidParameterException {
        if (!allMessages.containsKey(i)) {
            throw new InvalidParameterException("This message does not exist");
        }

        return allMessages.get(i);
    }

    public void sendMessage(User from, User to, String message) {
        Message m = new Message(from.getUsername(), to.getUsername(), message.trim(), numMessages);
        allMessages.put(m.getID(), m);
        numMessages ++;

        if (!to.getMessages().containsKey(from.getUsername())) {
            to.getMessages().put(from.getUsername(), new ArrayList<>());
        }
        to.getMessages().get(from.getUsername()).add(m.getID());

        if (!from.getMessages().containsKey(to.getUsername())) {
            from.getMessages().put(to.getUsername(), new ArrayList<>());
        }
        from.getMessages().get(to.getUsername()).add(m.getID());
    }

    public void massMessage(User from, ArrayList<User> to, String message) {
        for (User u : to) {
            sendMessage(from, u, message);
        }
    }

    public String getMessagingHistory(User from, User u) throws InvalidParameterException {
        if (!u.getMessages().containsKey(from.getUsername())) {
            throw new InvalidParameterException("You do not have any messages with this user");
        } else {
            StringBuilder ans = new StringBuilder();
            for (Integer i : u.getMessages().get(from.getUsername())) {
                ans.append(allMessages.get(i).toString()).append("\n");
            }
            return ans.toString();
        }
    }

}
