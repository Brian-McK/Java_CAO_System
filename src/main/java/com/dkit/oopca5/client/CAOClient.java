package com.dkit.oopca5.client;

// Brian McKenna - SD2B - Github: https://github.com/Brian-McK/BrianMcKenna_CA5/

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Colours;
import com.dkit.oopca5.core.DTO.Student;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import com.dkit.oopca5.server.CAOClientHandler;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import java.sql.Date;
import java.util.*;

public class CAOClient
{
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

        while (!RegexChecker.isValidCAONumber(caoNumberStr, CAOService.CAO_NUMBER_REGEX))
        {
            System.out.println("Invalid entry, please enter a valid CAO Number (8 digits): ");
            caoNumberStr = scan.nextLine();
        }
        int caoNumber = Integer.parseInt(caoNumberStr);
        System.out.println("CAO Number: " + caoNumber);
        // CAONumber e.g: 12345678

        System.out.println("Enter Date of Birth e.g 1990-12-05: ");
        String dobStr = scan.nextLine();

        while (!RegexChecker.isValidDOB(dobStr, CAOService.DATE_OF_BIRTH_REGEX))
        {
            System.out.println("Invalid entry, please enter date of birth again (yyyy-mm-dd): ");
            dobStr = scan.nextLine();
        }
        Date dobObj = Date.valueOf(dobStr);
        System.out.println("Date of Birth: " + dobObj.toString());
        // DOB e.g: 1990-12-05

        System.out.println("Enter Password: ");
        String password = scan.nextLine();

        while (!RegexChecker.isValidPassword(password, CAOService.PASSWORD_REGEX))
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

        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket

            System.out.println("Client message: The Client is running and has connected to the server");

            String msgForServer =
                    CAOService.REGISTER_COMMAND + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER + dobStr + CAOService.BREAKING_CHARACTER + password;

            System.out.println("Client Request: " + msgForServer);

            OutputStream os = socket.getOutputStream();
            PrintWriter out = new PrintWriter(os, true);

            out.write(msgForServer+"\n");  // write command to socket, and newline terminator
            out.flush();              // flush (force) the command over the socket

            Scanner inStream = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply

            String response = inStream.nextLine();

            if(response.equals(CAOService.SUCCESSFUL_REGISTER)){
                System.out.println(Colours.GREEN + "Successfully registered" + Colours.RESET);
            }
            else {
                System.out.println(Colours.RED + "Not registered" + Colours.RESET);
            }

            out.close();
            inStream.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }

    private void login()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("*** LOGIN SELECTED ***");
        System.out.println("*** To login, please enter your CAO Number, Date of Birth and your password ***");

        System.out.println("Enter CAO Number: ");
        String caoNumberStr = scan.nextLine();

        while (!RegexChecker.isValidCAONumber(caoNumberStr, CAOService.CAO_NUMBER_REGEX))
        {
            System.out.println("Invalid entry, please enter a valid CAO Number (8 digits): ");
            caoNumberStr = scan.nextLine();
        }
        int caoNumber = Integer.parseInt(caoNumberStr);
        System.out.println("CAO Number: " + caoNumber);
        // CAONumber e.g: 12345678

        System.out.println("Enter Date of Birth e.g 1990-12-05: ");
        String dobStr = scan.nextLine();

        while (!RegexChecker.isValidDOB(dobStr, CAOService.DATE_OF_BIRTH_REGEX))
        {
            System.out.println("Invalid entry, please enter date of birth again (yyyy-mm-dd): ");
            dobStr = scan.nextLine();
        }
        Date dobObj = Date.valueOf(dobStr);
        System.out.println("Date of Birth: " + dobObj.toString());
        // DOB e.g: 1990-12-05

        System.out.println("Enter Password: ");
        String password = scan.nextLine();

        while (!RegexChecker.isValidPassword(password, CAOService.PASSWORD_REGEX))
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

        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket

            System.out.println("Client message: The Client is running and has connected to the server");

        String msgForServer =
                CAOService.LOGIN_COMMAND + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER + dobStr + CAOService.BREAKING_CHARACTER + password;

        System.out.println("Client Request: " + msgForServer);

            OutputStream os = socket.getOutputStream();
            PrintWriter out = new PrintWriter(os, true);

            out.write(msgForServer+"\n");  // write command to socket, and newline terminator
            out.flush();              // flush (force) the command over the socket

            Scanner inStream = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply

            String response = inStream.nextLine();

        // if the students details are incorrect, SERVER RESPONSE LOGIN FAILED & send them back to the top
        // if the students details are correct, SERVER RESPONSE LOGGED IN then pass the student object to the
        // loggedIn method

            if(response.equals(CAOService.SUCCESSFUL_LOGIN)){
                System.out.println(Colours.GREEN + "Successfully logged in" + Colours.RESET);
                Student loggedInStudent = new Student(caoNumber, dobObj, password);
                loggedIn(loggedInStudent);
            }
            else {
                System.out.println(Colours.RED + "Not logged in" + Colours.RESET);
            }
            out.close();
            inStream.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }

    private void CAOCourseMenuLoop(Student student)
    {
        Scanner scan = new Scanner(System.in);

        CAOCourseMenu selectedOption = CAOCourseMenu.CONTINUE;
        while ((selectedOption != CAOCourseMenu.QUIT_APPLICATION) && (selectedOption != CAOCourseMenu.LOGOUT))
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

    private void loggedIn(Student student)
    {
        System.out.println("Welcome, You are now logged in CAO applicant: " + student.getCaoNumber());
        CAOCourseMenuLoop(student);
    }

    private void displayCourse()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Display Course Selected");

        System.out.println("Enter the Course ID to view the details of the course: ");
        String courseid = scan.nextLine();

        while (!RegexChecker.isValidCourseId(courseid, CAOService.COURSE_ID_REGEX))
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
        Scanner scan = new Scanner(System.in);

        System.out.println("Update Current Choices Selected");

        LinkedHashSet<String> newCourseChoicesHashSet = new LinkedHashSet<>();
        int counter = 1;

        System.out.println("Enter your new course choices in order of preference (duplicates will be removed): ");

        while (true)
        {
            System.out.println("Enter Course id for #" + counter + " choice: ");
            String newCourseChoiceId = scan.nextLine();

            if(newCourseChoiceId.equalsIgnoreCase("Exit"))
            {
                System.out.println("Exited");
                break;
            }

            while (!RegexChecker.isValidCourseId(newCourseChoiceId, CAOService.COURSE_ID_REGEX))
            {
                System.out.println("Invalid entry, please enter a valid Course ID (At least 1 number and 1 character): ");
                newCourseChoiceId = scan.nextLine();
            }
            newCourseChoicesHashSet.add(newCourseChoiceId);

            counter++;
        }
        ArrayList<String> newCourseChoicesNoDups = new ArrayList<>(newCourseChoicesHashSet);

        String msgForServer =
                CAOService.UPDATE_CURRENT_COMMAND +
                        CAOService.BREAKING_CHARACTER +
                        caoNumber + CAOService.BREAKING_CHARACTER +
                        newCourseChoicesNoDups.toString();

        System.out.println("Client Request: " + msgForServer);
    }

    private void logout()
    {
        System.out.println("Logout Selected");

        String msgForServer = CAOService.LOGOUT_COMMAND;

        System.out.println("Client Request: " + msgForServer);
    }
}
