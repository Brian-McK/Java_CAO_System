package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Colours;
import com.dkit.oopca5.core.DTO.Student;

import java.sql.Date;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CAOClient
{
    // put in dto courseChoices - ignore?
    // public List<String> findCoursesForUser(int caoNumber) throws DAOException;
    // TODO: 26/03/2021 - below method
    // public void updateCoursesForUser(int caoNumber, List<String> courses) throws DAOException;

    public static void main(String[] args)
    {
        System.out.println("*** WELCOME TO THE CAO ***");

        CAOClient client = new CAOClient();
        client.start();
    }

    private void start()
    {
        updateCurrentChoices(12345678);
//        mainMenuLoop();
    }

    private void printMainMenuOptions()
    {
        System.out.println("\nEnter your choice:");
        for (int i = 1; i < MainMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ") " + MainMenu.values()[i].toString() + Colours.RESET);
        }
    }

    private void printCAOCourseMenuOptions()
    {
        System.out.println("\nEnter your choice:");
        for (int i = 1; i < CAOCourseMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ") " + CAOCourseMenu.values()[i].toString() + Colours.RESET);
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
        Date dobObj = Date.valueOf(dobStr);
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

        System.out.println("Client Request: " + msgForServer);
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
        Date dobObj = Date.valueOf(dobStr);
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

        System.out.println("Client Request: " + msgForServer);

        // Check if the student is registered
        // Check if the students details are correct
        // if the students details are incorrect, SERVER RESPONSE LOGIN FAILED & send them back to the top
        // if the students details are correct, SERVER RESPONSE LOGGED IN then pass the student object to the
        // loggedIn method

        Student loggedInStudent = new Student(caoNumber, dobObj, password);
        loggedIn(loggedInStudent);
    }

    private void CAOCourseMenuLoop(Student student)
    {
        Scanner scan = new Scanner(System.in);

        CAOCourseMenu selectedOption = CAOCourseMenu.CONTINUE;
        while (selectedOption != CAOCourseMenu.QUIT_APPLICATION)
        {
            try{
                printCAOCourseMenuOptions();
                selectedOption = CAOCourseMenu.values()[Integer.parseInt(scan.nextLine().trim())];

                switch (selectedOption)
                {
                    case DISPLAY_COURSE:
                        displayCourse();
                        break;
                    case DISPLAY_ALL_COURSES:
                        displayAllCourses();
                        break;
                    case DISPLAY_CURRENT_CHOICES:
                        displayCurrentChoices(student.getCaoNumber());
                        break;
                    case UPDATE_CURRENT_CHOICES:
                        updateCurrentChoices(student.getCaoNumber());
                        break;
                    case LOGOUT:
                        logout();
                        break;
                    case QUIT_APPLICATION:
                        System.out.println("Quit Application Selected");
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

    // TODO: 30/03/2021 - NOT SURE ABOUT BELOW ARGUMENTS
    private void loggedIn(Student student)
    {
        System.out.println("Welcome, You are now logged in CAO applicant: " + student.getCaoNumber());
        CAOCourseMenuLoop(student);
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

    public static boolean isValidCourseId(String courseId, String courseIdRegex)
    {
        Pattern p = Pattern.compile(courseIdRegex);
        Matcher m = p.matcher(courseId);

        return m.matches();
    }

    private void displayCourse()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Display Course Selected");

        System.out.println("Enter the Course ID to view the details of the course: ");
        String courseid = scan.nextLine();

        while (!isValidCourseId(courseid, CAOService.COURSE_ID_REGEX))
        {
            System.out.println("Invalid entry, please enter a valid Course ID (At least 1 number and 1 character): ");
            courseid = scan.nextLine();
        }
        System.out.println("Course ID: " + courseid);
        // courseid e.g: DK821

        String msgForServer = CAOService.DISPLAY_COURSE_COMMAND + CAOService.BREAKING_CHARACTER + courseid;

        System.out.println("Client Request: " + msgForServer);
    }

    private void displayAllCourses()
    {
        System.out.println("Display All Courses Selected");

        String msgForServer = CAOService.DISPLAY_ALL_COURSES_COMMAND;

        System.out.println("Client Request: " + msgForServer);
    }

    private void displayCurrentChoices(int caoNumber)
    {
        System.out.println("Display Current Choices Selected");

        String msgForServer = CAOService.DISPLAY_CURRENT_COMMAND + CAOService.BREAKING_CHARACTER + caoNumber;

        System.out.println("Client Request: " + msgForServer);
    }

    private void updateCurrentChoices(int caoNumber)
    {
        System.out.println("Update Current Choices Selected");

        // Hashmap? key value pair - key = courseId, value = choice number?
        // have a breaking condition to exit the loop
        // ask them to enter courses in order of top choice to last choice

        List<String> courseChoices = new ArrayList<>();
        courseChoices.add("DK821");
        courseChoices.add("DK666");
        courseChoices.add("DK111");
        courseChoices.add("MAY212");

//        Map<String, Integer> courseChoices = new HashMap<>();
//        System.out.println("Enter course choices in order of preference: ");
//        int choiceNumber = 0;
//        courseChoices.put("DK821",++choiceNumber);
//        courseChoices.put("DK666",++choiceNumber);
//        courseChoices.put("DK111",++choiceNumber);
//        courseChoices.put("MAY212",++choiceNumber);

//        for (Map.Entry<String, Integer> entry : courseChoices.entrySet())
//        {
//            String courseID = entry.getKey();
//            int courseChoiceNumber = entry.getValue();
//            System.out.println("courseID: " + courseID + ", #" + courseChoiceNumber);
//        }

        System.out.println(courseChoices.toString());

        String oldCourseId = "OLD";

        String msgForServer =
                CAOService.UPDATE_CURRENT_COMMAND +
                        CAOService.BREAKING_CHARACTER +
                        caoNumber + CAOService.BREAKING_CHARACTER +
                        oldCourseId + CAOService.BREAKING_CHARACTER +
                        courseChoices.toString();

        System.out.println("Client Request: " + msgForServer);
    }

    private void logout()
    {
        System.out.println("Logout Selected");

        String msgForServer = CAOService.LOGOUT_COMMAND;

        System.out.println("Client Request: " + msgForServer);
    }
}
