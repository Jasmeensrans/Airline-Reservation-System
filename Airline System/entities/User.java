package entities;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate DOB;
    private String passportNumber;
    private BigInteger phoneNumber;
    private String username;
    private String password;
    private String email;
    private HashMap<String, ArrayList<Integer>> messages;
    private ArrayList<Integer> tickets;
    private UserType type;
    private String appearance="light";

    public User(String firstName, String lastName, LocalDate DOB, String passportNumber, BigInteger phoneNumber, String username, String password, String email, UserType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.email = email;
        this.messages = new HashMap<String, ArrayList<Integer>>();
        this.type = type;
        this.tickets = new ArrayList<>();
    }

    public HashMap<String, ArrayList<Integer>> getMessages() {
        return messages;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public BigInteger getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public UserType getType() {
        return type;
    }

    public ArrayList<Integer> getTickets() {
        return tickets;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }
}
