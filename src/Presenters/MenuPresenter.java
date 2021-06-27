package Presenters;

public class MenuPresenter {
    public void displayMenu() {
        System.out.println("1. Flight manager");
        System.out.println("2. Message manager");
        System.out.println("3. Logout");
    }

    public void displayAdminMenu() {
        System.out.println("1. Flight manager");
        System.out.println("2. Message manager");
        System.out.println("3. Create employee account");
        System.out.println("4. Delete account");
        System.out.println("5. Logout");
    }

    public void EmployeeCreated() {
        System.out.println("This employee was successfully created");
    }

    public void firstName() {
        System.out.println("Please enter the first name of the employee");
    }

    public void lastName() {
        System.out.println("Please enter the last name of the employee");
    }

    public void Passport() {
        System.out.println("Please enter the passport number of the employee");
    }

    public void username() {
        System.out.println("Please enter the username of the employee");
    }

    public void password() {
        System.out.println("Please enter the password of the employee");
    }

    public void email() {
        System.out.println("Please enter the email of the employee");
    }

    public void UsernameAlreadyExists() {
        System.out.println("This username already exists, please enter another username of \"back\" if you would like to return to the previous page");
    }

    public void PhoneNum() {
        System.out.println("Please enter the phone number of the employee");
    }

    public void InvalidPhoneNum() {
        System.out.println("You have entered an invalid phone number");
    }

    public void year() {
        System.out.println("Please enter the year this employee was born");
    }

    public void month() {
        System.out.println("Please enter the number representing the month this employee was born (note that single digit months are represented as 9 not 09)");
    }

    public void day() {
        System.out.println("Please enter the day this employee was born");
    }

    public void InvalidDOB() {
        System.out.println("You have entered an invalid DOB");
    }

    public void askForInput() {
        System.out.println("Please enter a number representing one of the options above");
    }

    public void optionError() {
        System.out.println("What you have entered is invalid, please enter a number representing one of the options above");
    }

    public void printUsers(String userString) {
        System.out.println(userString);
    }

    public void askForUsername() {
        System.out.println("Please enter the name of the username whose account you would like to delete");
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void userDeleted() {
        System.out.println("The user has been deleted");
    }

    public void cannotDelete() {
        System.out.println("The admin account cannot be deleted");
    }
}
