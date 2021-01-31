package Presenters;

public class SignUpPresenter {
    public void firstName() {
        System.out.println("Please enter your first name");
    }

    public void lastName() {
        System.out.println("Please enter your last name");
    }

    public void Passport() {
        System.out.println("Please enter your passport number");
    }

    public void username() {
        System.out.println("Please enter your username");
    }

    public void password() {
        System.out.println("Please enter your password");
    }

    public void email() {
        System.out.println("Please enter your email");
    }

    public void Exception(Exception e) {
        System.out.println(e.getMessage());
    }

    public void UsernameAlreadyExists() {
        System.out.println("This username is taken, please enter another one or back if you would like to return to the previous page");
    }

    public void PhoneNum() {
        System.out.println("Please enter your phone number with no spaces or hyphens");
    }

    public void InvalidPhoneNum() {
        System.out.println("This is not a valid phone number");
    }

    public void year() {
        System.out.println("Please enter the year you were born");
    }

    public void month() {
        System.out.println("Please enter the number representing the month you were born (single digit months should be entered as 9 not 09)");
    }

    public void day() {
        System.out.println("Please enter the day you were born");
    }

    public void InvalidDOB() {
        System.out.println("You have entered your date of birth incorrectly");
    }
}
