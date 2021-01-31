package Presenters;

public class EntryPresenter {
    public void welcomeMessage() {
        System.out.println("Welcome to the Airline reservation system");
        System.out.println("Enter 'quit' to exit the program");
    }

    public void menu() {
        System.out.println("Please enter a number representing one of the options below");
        System.out.println("1. Login");
        System.out.println("2. Signup for a new account");
    }

    public void InputError() {
        System.out.println("Please enter a number representing one of the options above");
    }
}
