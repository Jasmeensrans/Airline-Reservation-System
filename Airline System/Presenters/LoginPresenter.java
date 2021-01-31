package Presenters;

import Controllers.AbstractController;

public class LoginPresenter{
    public void askForUsername() {
        System.out.println("Enter username:");
    }

    public void userNotFound() {
        System.out.println("User not found. Please try again or enter \"sign up\" to create an account");
    }

    public void askForPassword() {
        System.out.println("Enter password: ");
    }

    public void incorrectPassword() {
        System.out.println("The password you have entered is incorrect. Please try again or enter \"back\" to go back to the main page");
    }
}
