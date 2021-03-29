package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Colours;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CAOClient
{
    // put in dto courseChoices - ignore?
    // public List<String> findCoursesForUser(int caoNumber) throws DAOException;
    // TODO: 26/03/2021 - below method
    // public void updateCoursesForUser(int caoNumber, List<String> courses) throws DAOException;

    // TODO: 24/03/2021 - TWO MENUS

    public static void main(String[] args)
    {
        System.out.println("*** WELCOME TO THE CAO ***");

        CAOClient client = new CAOClient();
        client.start();
    }

    private void start()
    {
        mainMenuLoop();
    }

    private void printMainMenuOptions()
    {
        System.out.println("\nEnter your choice:");
        for (int i = 1; i < MainMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ") " + MainMenu.values()[i].toString() + Colours.RESET);
        }
    }

    private void mainMenuLoop()
    {
        Scanner scan = new Scanner(System.in);

        MainMenu selectedOption = MainMenu.CONTINUE;
        while (selectedOption != MainMenu.QUIT_APPLICATION)
        {
            try{
                printMainMenuOptions();
                selectedOption = MainMenu.values()[Integer.parseInt(scan.nextLine().trim())];

                switch (selectedOption)
                {
                    case REGISTER:
                        register();
                        // check if the user does not already exist
                        break;
                    case LOGIN:
                        login();
                        break;
                    case QUIT_APPLICATION:
                        System.out.println("QUIT SELECTED");
                        break;
                    default:
                        System.out.println("Invalid entry, try again");
                }
            }
            catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Invalid entry, try again");
            }
        }
    }

    private void register()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("*** REGISTER SELECTED ***");
        System.out.println("*** To register, please enter your CAO Number, Date of Birth and your password ***");

        System.out.println("Enter CAO Number: ");
        String caoNumberStr = scan.nextLine();

        while (!isValidCAONumber(caoNumberStr, CAOService.CAO_NUMBER_REGEX))
        {
            System.out.println("Invalid entry, please enter a valid CAO Number (8 digits): ");
            caoNumberStr = scan.nextLine();
        }
        int caoNumber = Integer.parseInt(caoNumberStr);
        System.out.println("CAO Number: " + caoNumber);
        // CAONumber e.g: 12345678

        System.out.println("Enter Date of Birth: ");
        String dobStr = scan.nextLine();

        while (!isValidDOB(dobStr, CAOService.DATE_OF_BIRTH_REGEX))
        {
            System.out.println("Invalid entry, please enter date of birth again (yyyy-mm-dd): ");
            dobStr = scan.nextLine();
        }
        LocalDate dobObj = LocalDate.parse(dobStr);
        System.out.println("Date of Birth: " + dobObj.toString());
        // DOB e.g: 1990-12-05

        System.out.println("Enter Password: ");
        String password = scan.nextLine();

        while (!isValidPassword(password, CAOService.PASSWORD_REGEX))
        {
            System.out.println("Invalid entry, Password must Contain: " + "\n" +
                    "- A digit" + "\n" +
                    "- A Lowercase letter" + "\n" +
                    "- An uppercase letter" + "\n" +
                    "- A special character" + "\n" +
                    "- No Whitespace" + "\n" +
                    "- At least 8 characters in length");
            password = scan.nextLine();
        }
        // Password e.g: Brian!?123#

        String msgForServer =
                CAOService.REGISTER_COMMAND + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER + dobStr + CAOService.BREAKING_CHARACTER + password;

        System.out.println("Message ready for server: " + msgForServer);
    }

    private void login()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("*** LOGIN SELECTED ***");
        System.out.println("*** To login, please enter your CAO Number, Date of Birth and your password ***");

        System.out.println("Enter CAO Number: ");
        String caoNumberStr = scan.nextLine();

        while (!isValidCAONumber(caoNumberStr, CAOService.CAO_NUMBER_REGEX))
        {
            System.out.println("Invalid entry, please enter a valid CAO Number (8 digits): ");
            caoNumberStr = scan.nextLine();
        }
        int caoNumber = Integer.parseInt(caoNumberStr);
        System.out.println("CAO Number: " + caoNumber);
        // CAONumber e.g: 12345678

        System.out.println("Enter Date of Birth: ");
        String dobStr = scan.nextLine();

        while (!isValidDOB(dobStr, CAOService.DATE_OF_BIRTH_REGEX))
        {
            System.out.println("Invalid entry, please enter date of birth again (yyyy-mm-dd): ");
            dobStr = scan.nextLine();
        }
        LocalDate dobObj = LocalDate.parse(dobStr);
        System.out.println("Date of Birth: " + dobObj.toString());
        // DOB e.g: 1990-12-05

        System.out.println("Enter Password: ");
        String password = scan.nextLine();

        while (!isValidPassword(password, CAOService.PASSWORD_REGEX))
        {
            System.out.println("Invalid entry, Password must Contain: " + "\n" +
                    "- A digit" + "\n" +
                    "- A Lowercase letter" + "\n" +
                    "- An uppercase letter" + "\n" +
                    "- A special character" + "\n" +
                    "- No Whitespace" + "\n" +
                    "- At least 8 characters in length");
            password = scan.nextLine();
        }
        // Password e.g: Brian!?123#

        String msgForServer =
                CAOService.LOGIN_COMMAND + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER + dobStr + CAOService.BREAKING_CHARACTER + password;

        System.out.println("Message ready for server: " + msgForServer);

        // Check if the student is registered
        // Check if the students details are correct
        // if the students details are not correct, SERVER RESPONSE LOGIN FAILED & send them back to the top
        // if the students details are correct, SERVER RESPONSE LOGGED IN

        loggedIn(caoNumber, dobObj, password);
    }

    private void CAOCourseMenuLoop()
    {
        Scanner scan = new Scanner(System.in);

        CAOCourseMenu selectedOption = CAOCourseMenu.CONTINUE;
        while (selectedOption != CAOCourseMenu.QUIT_APPLICATION)
        {
            try{
                printMainMenuOptions();
                selectedOption = CAOCourseMenu.values()[Integer.parseInt(scan.nextLine().trim())];

                switch (selectedOption)
                {
                    case DISPLAY_COURSE:
                        System.out.println("Display Course Selected");
                        break;
                    case DISPLAY_ALL_COURSES:
                        System.out.println("Display All Courses Selected");
                        break;
                    case DISPLAY_CURRENT_CHOICES:
                        System.out.println("Display Current Choices Selected");
                        break;
                    case UPDATE_CURRENT_CHOICES:
                        System.out.println("Update Current Choices Selected");
                        break;
                    case LOGOUT:
                        System.out.println("Logout Selected");
                        break;
                    default:
                        System.out.println("Invalid entry, try again");
                }
            }
            catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Invalid entry, try again");
            }
        }
    }

    private void loggedIn(int caoNumber, LocalDate dobObj, String password)
    {
        System.out.println("You are now logged in " + caoNumber);
        CAOCourseMenuLoop();
    }

    public static boolean isValidCAONumber(String caoNumber, String caoNumberRegex)
    {
        Pattern p = Pattern.compile(caoNumberRegex);
        Matcher m = p.matcher(caoNumber);

        return m.matches();
    }

    public static boolean isValidDOB(String dobStr, String dobRegex)
    {
        Pattern p = Pattern.compile(dobRegex);
        Matcher m = p.matcher(dobStr);

        return m.matches();
    }

    public static boolean isValidPassword(String password, String passwordRegex)
    {
        Pattern p = Pattern.compile(passwordRegex);
        Matcher m = p.matcher(password);

        return m.matches();
    }
}
