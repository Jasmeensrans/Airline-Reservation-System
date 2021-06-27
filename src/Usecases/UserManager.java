package Usecases;

import entities.User;
import entities.UserType;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.Set;

public class UserManager implements Serializable {

    private HashMap<String, User> allUsers;
    private User adminUser = new User("admin", "account", LocalDate.now(), "123", BigInteger.ONE, "admin_account", "password123", "admin_account@email.com", UserType.ADMIN);

    public UserManager() {
        allUsers = new HashMap<>();
        allUsers.put(adminUser.getUsername(), adminUser);
    }

    public User getUser(String username) throws InvalidParameterException {
        if (allUsers.containsKey(username)) {
            return allUsers.get(username);

        } else {
            throw new InvalidParameterException("This user does not exist");
        }
    }

    public boolean userExists(String username) {
        return allUsers.containsKey(username);
    }

    public void createUser(String firstName, String lastName, LocalDate DOB, String passportNum, BigInteger phoneNum, String username, String password, String email, UserType type) {
        User u = new User(firstName.trim(), lastName.trim(), DOB, passportNum.trim(), phoneNum, username.trim(), password.trim(), email.trim(), type);
        allUsers.put(u.getUsername(), u);

    }


    public boolean loginVerify(User user, String passwordIn) {
        return user.getPassword().equals(passwordIn.trim());
    }

    public UserType Passenger() {
        return UserType.PASSENGER;
    }

    public UserType Admin() {
        return UserType.ADMIN;
    }

    public ArrayList<User> convertToUser(ArrayList<String> users) {
        ArrayList<User> ans = new ArrayList<>();
        for (String u : users) {
            ans.add(getUser(u));
        }
        return ans;
    }

    public String userString() {
        Set<String> users = allUsers.keySet();
        StringBuilder ans = new StringBuilder();
        for(String u : users){
            ans.append(u);
            ans.append("\n");
        }
        return ans.toString();
    }

    public void deleteUser(User user){
        allUsers.remove(user.getUsername());
        Set<String> users = allUsers.keySet();
        for(String u : users){
            getUser(u).getMessages().remove(user.getUsername());
        }
    }

    public Collection<String> getAllUsers() {
        return allUsers.keySet();
    }
}

