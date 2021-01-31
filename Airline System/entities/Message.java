package entities;

import java.io.Serializable;

public class Message implements Serializable {

    private String from;
    private String to;
    private String message;
    private Integer ID;

    public Message(String from, String to, String message, Integer ID){
        this.from = from;
        this.to = to;
        this.message = message;
        this.ID = ID;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public Integer getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "From: " + from + "\n" + "To: " + to + "\n" + message + "\n";
    }
}

