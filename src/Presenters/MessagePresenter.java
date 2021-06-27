package Presenters;

public class MessagePresenter {
    public void displayAdminMenu() {
        System.out.println("1. Message flight");
        System.out.println("2. Message user");
        System.out.println("3. View messaging history");
        System.out.println("4. Back");
    }

    public void flightMessaged() {
        System.out.println("Your message has been successfully sent to the passengers of this flight");
    }

    public void displayPassengerMenu() {
        System.out.println("1. View messaging history");
        System.out.println("2. Message flight manager");
        System.out.println("3. Back");
    }

    public void messageSent() {
        System.out.println("Your message has been sent successfully");
    }

    public void printFlights(String flightsToString) {
        System.out.println(flightsToString);
    }

    public void selectFlight() {
        System.out.println("Please select the flight you would like to message");
    }

    public void enterMessage() {
        System.out.println("Please enter the message you would like to send");
    }

    public void notFlightManager() {
        System.out.println("You are not the flight manager of this flight");
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void enterUsername() {
        System.out.println("Please enter the username of the user you would like to message");
    }

    public void UserMessagingHistory() {
        System.out.println("Please enter the username of the user whose messaging history you would like to view");
    }

    public void printMessage(String messagingHistory) {
        System.out.println(messagingHistory);
    }

    public void enterFlightNum() {
        System.out.println("Please enter the flight number of the flight whose manager you would like to message");
    }

    public void userNotOnFLight() {
        System.out.println("You are not on this flight");
    }

    public void optionPrompt() {
        System.out.println("Please enter a number representing one of the options above");
    }

    public void optionError() {
        System.out.println("What you have entered is invalid, please enter a number representing one of the options");
    }
}
